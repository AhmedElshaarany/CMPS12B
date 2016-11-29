//-----------------------------------------------------------------------------
// Simulation.java
//-----------------------------------------------------------------------------
//----------------------------------------------------------------------------                           
// Name:        Ahmed Elshaarany                                                                         
// CruzID:      aelshaar                                                                                 
// Class:       12B                                                                                      
// Assignment:  pa5                                                                                      
//-----------------------------------------------------------------------------                          
// Description:                                                                                          
// This class contains the main function that implements the algorithm for
// job processing, the algorithm closely, but not exactly, the algorithm
// suggested in the SimulationStub.java file on the class website. The comments
// in the file are an attempt to explain exaclty how the algorithm works.
// Some helper functions were used to implement the algorithm. Their
// implementation and some comments about them are provided after the main
// function
//-----------------------------------------------------------------------------                          


import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

//-----------------------------------------------------------------------------
//
// ***************************** main method **********************************
//    
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

       // check command line arguments
       // if no input file is specified 
       if ( args.length != 1 ){
	   System.err.println("Usage: Simulation <input_file>");
	   System.exit(1);
       }


       // open files for reading and writing
       Scanner in;
       PrintWriter report;
       PrintWriter trace;
       
       // try to open input file to read 
       try{
	   in = new Scanner(new File(args[0]));
       }
       catch(IOException e){
	   // throw IOException if failed to open input file
	   throw new IOException("Failed to open specified input file");
       }

       // try to create the output files
       try{
	   report = new PrintWriter(new FileWriter(args[0]+".rpt"));
	   trace  = new PrintWriter(new FileWriter(args[0]+".trc"));
       }
       catch(IOException e){
	   // throw IOException if failed to create output files
	   throw new IOException("Failed to create report and/or trace file(s)");
       }
       

       // read in m jobs from input file
       // declare needed variables
       int m = in.nextInt();
       Queue backupQ  = new Queue();
       Queue storageQ = new Queue();       

       // move to next line
       in.nextLine();
       
       // start reading from input file
       for (int i = 0; i < m; i++){
	   Job j = new Job(in.nextInt(), in.nextInt());
	   backupQ.enqueue(j);
	   storageQ.enqueue(j);
       }

       // Write first few lines in trace and report files
       initializeOutputFiles(trace, report, args[0], m, storageQ);

       // run simulation with n processors for n=1 to n=m-1  {
       for(int n = 1; n < m; n++){

	   // print number of processors to trace file
	   trace.println("*****************************");
	   if ( n == 1 ){
	       trace.println(n+" processor:");
	   }
	   else{
	       trace.println(n+" processors:");
	   }
	   trace.println("*****************************");	   

	   // declare time variables
	   int time               = 0;
	   int nextArrivalTime    = 0;
	   int nextFinishTime     = 0;

	   // declare jobs counting variables
	   int finishedJobsCount  = 0;
	   int jobsBeingProcessed = 0;

	   // declare array of n processors
	   Queue[] processorQ = new Queue[n];

	   // initialize array of n processors
	   for(int i = 0; i < n; i++){
	       processorQ[i] = new Queue();
	   }
	   
	   // reset storageQ for each simulation run
	   if( storageQ.isEmpty() ){
	       for(int i = 0; i < m; i++){
		   Job J = (Job) backupQ.dequeue();
		   J.resetFinishTime();
		   backupQ.enqueue(J);
		   storageQ.enqueue(J);
	       }
	   }

	   // print status of storage and processor queues
	   printStatus(trace, time, storageQ, processorQ);

	   // As long as number of fininshed jobs is less than total, keep processing
	   while ( finishedJobsCount < m ){
       
	       // determine the time of the next arrival or finish event and 
	       // update time

	       // if jobs being processed is less than total, check both finish and arrival times
	       if( jobsBeingProcessed < m ){

		   nextFinishTime  = findNextFinishTime(processorQ);
		   nextArrivalTime = findNextArrivalTime(storageQ);

		   // if a Job is finishing now, then complete them
		   if( nextFinishTime < nextArrivalTime && nextFinishTime != Job.UNDEF ){
		       finishedJobsCount += completeFinishedJobs(trace, processorQ, storageQ, nextFinishTime);
		       // update time
		       time = nextFinishTime;
       		       // print current status of queues to trace file
		       printStatus(trace, time, storageQ, processorQ);
		   }
		   // else if jobs are arriving now, add them to processing queues
		   else if ( nextFinishTime > nextArrivalTime || nextFinishTime == Job.UNDEF ){
		       time = nextArrivalTime;
		       jobsBeingProcessed += addArrivingJobToProcessorQ(trace, processorQ, storageQ, nextArrivalTime);
		       	// print current status of queues to trace file
		       printStatus(trace, time, storageQ, processorQ);
		   }
		   // else if some jobs are finishing and others are arriving at the same time,
		   // finish finished jobs first, then take care of the arriving jobs
		   else if ( nextFinishTime == nextArrivalTime ){
		       finishedJobsCount += completeFinishedJobs(trace, processorQ, storageQ, nextFinishTime);
		       jobsBeingProcessed += addArrivingJobToProcessorQ(trace, processorQ, storageQ, nextArrivalTime);
		       time = nextFinishTime;
       		       // print current status of queues to trace file
		       printStatus(trace, time, storageQ, processorQ);
		   }

	       }
	       // otherwise, check just the next finish time
	       else{
		   nextFinishTime  = findNextFinishTime(processorQ);
		   finishedJobsCount += completeFinishedJobs(trace, processorQ, storageQ, nextFinishTime);
		   // update time
		   time = nextFinishTime;
     		   // print current status of queues to trace file
	           printStatus(trace, time, storageQ, processorQ);
	       }
	  
	   }

	   // calculate and report statistics to report file
	   // this will also dequeue all jobs in storageQ
	   // and set it up for next simulation run
	   calculateStatsAndReport(report, storageQ, n);

       }

       
       // close input file
     	   in.close();
       // close output files
           report.close();
	   trace.close();
   }

//-----------------------------------------------------------------------------
//
// ***************************** Helper Methods *******************************
//    
//-----------------------------------------------------------------------------

    
    // findNextFinishTime()
    // A private helper method that calculates the next finsh
    // returns UNDEF if no jobs are in processorQs
    private static int  findNextFinishTime(Queue[] processorQ){
	int nextFinishTime = Job.UNDEF;

	for(int i = 0; i < processorQ.length; i++){
	    // if processorQ[i] is not empty, find next job finish time
	    if ( !processorQ[i].isEmpty() ){
		Job J = (Job)processorQ[i].peek();
		// if first time to store a finish time
		if ( nextFinishTime == Job.UNDEF ){
		    nextFinishTime = J.getFinish();
		}
		// else, find the least finish time in all processorQs
		else if ( nextFinishTime > J.getFinish() ){
		    nextFinishTime = J.getFinish();
		}
	    }
	}
	
	return (nextFinishTime);
    }

    
    // findNextArrivalTime()
    // A private helper method that returns the next arrival time
    private static int  findNextArrivalTime(Queue storageQ){
	return (((Job)storageQ.peek()).getArrival());
    }

    
    // completeFinishedJobs()
    // A private helper method that completes jobs that finished now
    private static int completeFinishedJobs(PrintWriter trace, Queue[] processorQ, Queue storageQ, int nextFinishTime){

	int finishedJobsCount = 0;
	
	// find jobs in all processor queues that finish now, complete them, and add them to storageQ
	for(int i =0; i < processorQ.length; i++){

	    // if processorQ[i] is not empty
	    if ( !processorQ[i].isEmpty() ){
		// if job at head of processorQ[i] is supposed to finish now
		if ( ((Job)processorQ[i].peek()).getFinish() ==  nextFinishTime ){
		    // remove job from processorQ[i]
		    Job J = (Job)processorQ[i].dequeue();
		    // increment number of jobs finihsed
		    finishedJobsCount++;
		    // add it to storageQ
		    storageQ.enqueue(J);

		    // if processorQ[i] is not Empty after dequeuing job, update finish its finish time
		    if ( !processorQ[i].isEmpty() ){
			((Job)processorQ[i].peek()).computeFinishTime(nextFinishTime);
		    }
		}
	    }
	}
	
	return (finishedJobsCount);
    }


    // addArrivingJobToProcessorQ()
    // A private helper method that adds the arriving job(s) at next ArrivalTime to
    // Queue(s) of minimum length and with lowest index in the queue array (i.e. an
    // adequate queue)
    private static int addArrivingJobToProcessorQ(PrintWriter trace, Queue[] processorQ, Queue storageQ, int nextArrivalTime){

	int jobsBeingProcessed = 0;
	// find all jobs that arrive now and and add them to an adequate queue
	// using the loop and a half technique
	while( true ){

	    // if next job's arrivaltime is not equal nextArrival time, then break
	    if( storageQ.isEmpty() || ((Job)storageQ.peek()).getArrival() != nextArrivalTime ){
		break;
	    }

	    // dequeue job from storageQ
	    Job J = (Job)storageQ.dequeue();

	    // find the queue of minimum length and lowest index
	    int index = findBestQ(processorQ);

	    // if best queue is empty, udpate finish time of that job
	    if( processorQ[index].isEmpty() ){
		J.computeFinishTime(nextArrivalTime);
	    }

	    // enqueue Job to best queue
	    processorQ[index].enqueue(J);

	    jobsBeingProcessed++;
	}
	
	return jobsBeingProcessed;
    }

    
    // findBestQ()
    // A helper private method that finds the best qeueue and returns its index
    private static int findBestQ(Queue[] processorQ){

	int bestQIndex = -1;
	int bestQLength = -1;
	int qIndex = 0;
	boolean found = false;
	

	while ( !found && qIndex < processorQ.length ){
	    
	    if ( processorQ[qIndex].isEmpty() ){
		bestQIndex = qIndex;
		found = true;
	    }
	    else{
		if( bestQIndex == -1 ){
		    bestQIndex = qIndex;
		    bestQLength = processorQ[qIndex].length();
		}
		else if ( processorQ[qIndex].length() < bestQLength ){
		    bestQIndex = qIndex;
		    bestQLength = processorQ[qIndex].length();
		}
	    }
	    qIndex++;
	}

	return (bestQIndex);
    }


    // printStatus()
    // A helper private method that prints the current status of storage
    // and processor queues to the trace file
    private static void printStatus(PrintWriter trace, int time, Queue storageQ, Queue[] processorQ){

	trace.println("time="+time);
	trace.println("0: "+storageQ.toString());
	for(int i = 1; i <= processorQ.length ; i++){
	    trace.println(i+": "+processorQ[i-1].toString());
	}
	trace.println();
    }


    // initializeFile()
    // A helper private method that writes the first four lines in the printToFile
    private static void initializeOutputFiles(PrintWriter trace, PrintWriter report, String fileName, int m, Queue storageQ){

       // print first four lines of trace file
       trace.println("Trace file: "+fileName+".trc");
       trace.println(m+" Jobs:");
       trace.println(storageQ.toString());
       trace.println();

       // print first five lines of report file
       report.println("Report file: "+fileName+".rpt");
       report.println(m+" Jobs:");
       report.println(storageQ.toString());
       report.println();
       report.println("***********************************************************");
    }


    // calculateStatsAndReport()
    // A helper private method that calculates statistics for simulation run
    // and reports it to the report file
    private static void calculateStatsAndReport(PrintWriter report, Queue storageQ, int n){

	// initialize statistics
	int   totalWait   = 0;
	int   maxWait     = 0;
	int   numJobs     = storageQ.length();
	float averageWait = 0;

	// for all jobs in 
	for(int i = 0; i < numJobs; i++){

	    // dequeue job
	    Job J = (Job)storageQ.dequeue();

	    // add wait time of job to total wait time
	    totalWait += J.getWaitTime();

	    // if wait time of current job is greater that maxWait, update maxWait
	    if( J.getWaitTime() > maxWait ){
		maxWait = J.getWaitTime();
	    }
	}

	// calculate average
	averageWait = (float)totalWait/(float)numJobs;

	// print to report file, and set averageWait precision to two decimals
	if( n == 1 ){
	    report.println(n+" processor: totalWait="+totalWait+", maxWait="+maxWait+", averageWait="+String.format("%.2f", averageWait));
	}
	else{
	    report.println(n+" processors: totalWait="+totalWait+", maxWait="+maxWait+", averageWait="+String.format("%.2f", averageWait));
	}
    }
}





