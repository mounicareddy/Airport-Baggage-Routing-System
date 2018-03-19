package airport.baggage.system;

import java.util.ArrayList;

public class Conveyor_System {
	public int[][] edges;//stores the edges between vertices(adjacency matrix)
	public Object[] labels;//stores the node name as labels
	public int N;//number of nodes
	private static final int NO_PARENT = -1;//initial parent
	
	public Conveyor_System(int n){//constructor initializing edges&labels array and value of N
		edges=new int[n][n];
		labels=new Object[n];
		N=n;
	}
	public void createEdge(int src,int dest,int w) {//creating bidirectional edge
		edges[src][dest]=w;//storing as bidirectional edge
		System.out.println(labels[src]+" "+labels[dest]+" "+w);
		edges[dest][src]=w;//storing as bidirectional edge
	}
	public void setLabel(int vertex,Object label) {//setting labels to each node/vertex
		labels[vertex]=label;
		
	}
	public void construct_Labels(int n) {//Automatic creation of labels based on indexes
		for(int i=0;i<n;i++) {
			if(i>0&&i<=10)
				setLabel(i,("A"+i));
			else if(i==0)
				setLabel(i,"Concourse_A_Ticketing");
			else
				setLabel(i,"BaggageClaim");
		}
	}
	public void construct_Edges() {//constructing weighted edges
		createEdge(0,5,5);
		createEdge(5,11,5);
		createEdge(5,10,4);
		createEdge(5,1,6);
		createEdge(1,2,1);
		createEdge(2,3,1);
		createEdge(3,4,1);
		createEdge(10,9,1);
		createEdge(9,8,1);
		createEdge(8,7,1);
		createEdge(7,6,1);
	}
	public void printGraph() {
		for(int i=0;i<edges.length;i++) {		
			for(int j=0;j<edges[i].length;j++) {
				if (edges[i][j]>0) System.out.println(labels[i]+" ->"+labels[j]+":"+edges[i][j]+" ");
			}
			System.out.println("-------------------");
		}
	}
	int minDistance(int dist[], Boolean sptSet[])//finds minimum dist vertex which is not included in shortest path
    {// Initializing minimum value
        int min = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < N; v++)
            if (sptSet[v] == false && dist[v] <= min)//dist must be minimum & vertex v should be in shortest path before
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
	void printSolution(int dist[], int n,int src,int dest,String BagNum,int parents[])
    {   
        for (int i = 0; i < N; i++) {
        	if(i==dest) {
        		System.out.print(BagNum+" ");
        		printPath(i,parents);
        		System.out.print(":"+dist[i]);
        	}
        	
        }
    }
	public void printPath(int v, int[] parents)//prints parent nodes or path tree
	{
		if (v == NO_PARENT)
			return;
		printPath(parents[v], parents);
		System.out.print(labels[v] + " ");
	}
	 void dijkstra(int graph[][], int src,int dest,String bagNum)//followed dijktras algorithm for shortest path
	    {
	        int dist[] = new int[N]; // The output array. dist[i] will hold the shortest distance from src to i
	        // sptSet[i] will true if vertex i is included in shortest path tree or shortest distance from src to i is finalized
	        Boolean sptSet[] = new Boolean[N];
	        // Initialize all distances as INFINITE and stpSet[] as false
	        for (int i = 0; i < N; i++)
	        {
	            dist[i] = Integer.MAX_VALUE;
	            sptSet[i] = false;
	        }
	 
	        // Distance of source vertex from itself is always 0
	        dist[src] = 0;
	     // Parent array to store shortest path
	        int[] parents = new int[N];
	     // The starting vertex does not have a parent
	        parents[src] = NO_PARENT;
	        // Find shortest path for all vertices
	        for (int count = 0; count < N-1; count++)
	        {
	            // Pick the minimum distance vertex from the set of vertices not yet processed. u is always equal to src in first iteration.
	            int u = minDistance(dist, sptSet);
	         // Mark the picked vertex as processed
	            sptSet[u] = true;
	            // Update dist value of the adjacent vertices of the picked vertex
	            for (int v = 0; v < N; v++)
	                // Update dist[v] only if is not in sptSet, there is an edge from u to v, and total weight of path from src to v through u is smaller than current value of dist[v]
	                if (!sptSet[v] && graph[u][v]!=0 &&dist[u] != Integer.MAX_VALUE && dist[u]+graph[u][v] < dist[v]) {
	                	 parents[v] = u;
	                    dist[v] = dist[u] + graph[u][v];
	                }
	        }
	        // prints the calculated distance array
	        printSolution(dist, N,src,dest,bagNum,parents);
	    }
	public void input(ArrayList<Departures> departureList,ArrayList<Bags> bagList) {
		//Adding departure information  to departureList and baggage information to bagList
		Departures d1=new Departures("UA10","A1","MIA","08:00");
		Departures d2=new Departures("UA11","A1","LAX","09:00");
		Departures d3=new Departures("UA12","A1","JFK","09:45");
		Departures d4=new Departures("UA13","A2","JFK","08:30");
		Departures d5=new Departures("UA14","A2","JFK","09:45");
		Departures d6=new Departures("UA15","A2","JFK","10:00");
		Departures d7=new Departures("UA16","A3","JFK","09:00");
		Departures d8=new Departures("UA17","A4","MHT","09:15");
		Departures d9=new Departures("UA18","A5","LAX","10:15");
		departureList.add(d1);
		departureList.add(d2);
		departureList.add(d3);
		departureList.add(d4);
		departureList.add(d5);
		departureList.add(d6);
		departureList.add(d7);
		departureList.add(d8);
		departureList.add(d9);
		
		Bags b1=new Bags("0001","Concourse_A_Ticketing","UA12");
		Bags b2=new Bags("0002","A5","UA17");
		Bags b3=new Bags("0003","A2","UA10");
		Bags b4=new Bags("0004","A8","UA18");
		Bags b5=new Bags("0005","A7","ARRIVAL");
		bagList.add(b1);
		bagList.add(b2);
		bagList.add(b3);
		bagList.add(b4);
		bagList.add(b5);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=12;
		Conveyor_System cs=new Conveyor_System(n);
		System.out.println("INPUT:");
		System.out.println();
		cs.construct_Labels(n);
		System.out.println("# Section: Conveyor System");
		System.out.println("-------------------");
		cs.construct_Edges();
		//cs.printGraph();
		
		ArrayList<Departures> departureList=new ArrayList<Departures>();
		ArrayList<Bags> bagList=new ArrayList<Bags>();
		cs.input(departureList,bagList);
		System.out.println();
		System.out.println("# Section: Departures");
		System.out.println("-------------------");
		for(int i=0;i<departureList.size();i++) {
			System.out.println(departureList.get(i).flight_id+" "+departureList.get(i).flight_gate+" "+departureList.get(i).destination+" "+departureList.get(i).flight_time);
		}
		System.out.println();
		System.out.println("# Section: Bags");
		System.out.println("-------------------");
		for(int i=0;i<bagList.size();i++) {
			System.out.println(bagList.get(i).bag_Number+" "+bagList.get(i).entry_point+" "+bagList.get(i).flight_id);
		}
		System.out.println();
		//OUTPUT
		System.out.println("OUTPUT :");
		System.out.println("-------------------");
		for(int k=0;k<bagList.size();k++) {//for all items in bagList calculate shortest path
		String source=bagList.get(k).entry_point;
		int src_indx=1,dest_indx=0;
		String flightGate="";
		String  flightNo=bagList.get(k).flight_id;
		String bagNum=bagList.get(k).bag_Number;
		for(Departures d:departureList) {
			if(d.flight_id==flightNo) {
				 flightGate=flightGate+d.getFlight_gate();
			}
		}
		//finding index of an source and destination from labels[]
		for(int i=0;i<cs.labels.length;i++) {
			if(source.equals(cs.labels[i])) {
				src_indx=i;
			}
			if(flightGate.equals(cs.labels[i])) {
				dest_indx=i;
			}
			}
			if(flightNo.equalsIgnoreCase("ARRIVAL"))//checks flightId equals to ARRIVAL then directed to baggageClaim
				dest_indx=11;
		cs.dijkstra(cs.edges, src_indx,dest_indx,bagNum);
		System.out.println();
		}

	}

}
