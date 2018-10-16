import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Main{

	double minLong = Integer.MAX_VALUE;
	double maxLong = Integer.MIN_VALUE;
	double minLat = Integer.MAX_VALUE;
	double maxLat = Integer.MIN_VALUE;
	double scaleX = 0;
	double scaleY = 0;
	public void show(boolean b) {
		if(b) {
			canvas can = new canvas();
		}
	}
	protected class Paint extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			int w2 = getWidth() / 2;
			int h2 = getHeight() / 2;
			g2d.rotate(-Math.PI, w2, h2);

			Iterable<Edge> it = g2.edges();
			for(Edge e : it) {
				Vertex v1 = map3.get((e.either()));
				Vertex v2 = map3.get(e.other(e.either()));
				double v1x = -((v1.longitude - minLong)/scaleY) * (this.getWidth()) + this.getWidth();
				double v1y = ((v1.latitude - minLat)/scaleX) * (this.getHeight());
				double v2x = -((v2.longitude - minLong)/scaleY) * (this.getWidth())+ this.getWidth();
				double v2y = ((v2.latitude - minLat)/scaleX) * (this.getHeight());

				g2d.drawLine((int)v1x,(int) v1y,(int) v2x,(int) v2y);
			}
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(Color.MAGENTA);
			for(Vertex v1 : path) {
				if(v1.previous != null) {
					Vertex v2 = v1.previous;
					double v1x = -((v1.longitude - minLong)/scaleY) * this.getWidth()+ this.getWidth();
					double v1y = ((v1.latitude - minLat)/scaleX) * this.getHeight();
					double v2x = -((v2.longitude - minLong)/scaleY) * this.getWidth()+ this.getWidth();
					double v2y = ((v2.latitude - minLat)/scaleX) * this.getHeight();	
					g2d.drawLine((int)v1x,(int) v1y,(int) v2x,(int) v2y);
				}
			}
		}
	}
	protected class canvas extends JFrame{
		Paint painter = new Paint();
		Graphics g = painter.getGraphics();
		public canvas() {
			setTitle("Map");
			setSize(600, 600);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(painter);
		}
	}
	public void setScale() {
		for(Vertex vertex : map.keySet()){
			if(vertex.latitude<minLat) {
				minLat = vertex.latitude;
			}
			if(vertex.latitude>maxLat) {
				maxLat = vertex.latitude;
			}
			if(vertex.longitude<minLong) {
				minLong = vertex.longitude;
			}
			if(vertex.longitude>maxLong) {
				maxLong = vertex.longitude;
			}
		}
		scaleX = maxLat - minLat;
		scaleY = maxLong - minLong;

	}
	/**
	 * Jason Winn
	 * http://jasonwinn.org
	 * Created July 10, 2013
	 * Call in a static context:
	 * Haversine.distance(47.6788206, -122.3271205,
	 *                    47.6788206, -122.5271205)
	 * --> 14.973190481586224 [km]
	 *
	 */
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	public static double distance(double startLat, double startLong, double endLat, double endLong) {

		double dLat  = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat   = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}

	public static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}


	public class Vertex{
		String name;
		double latitude;
		double longitude;
		double distance = Double.MAX_VALUE;
		Vertex previous = null;
		boolean visited = false;
		public Vertex(String name, double latitude, double longitude) {
			this.name = name;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		public String toString() {
			return name;
		}
	}
	public EdgeWeightedGraph createGraph(String Filename) {
		map3 = new HashMap<>();
		map2 = new HashMap<>();
		map = new HashMap<>();
		int counter = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(Filename));
			while(br.ready()) {
				String strLine = br.readLine();
				String[] line = strLine.split("\\s+");
				if(line[0].equals("i")) {
					Vertex v = new Vertex(line[1], Double.parseDouble(line[2]), Double.parseDouble(line[3]));
					map3.put(counter, v);
					map2.put(line[1], v);
					map.put(v, counter);
					counter++;
				}
				else if(line[0].equals("r")) {
					break;
				}
			}
			g2 = new EdgeWeightedGraph(map.size());
			while(br.ready()) {
				String strLine = br.readLine();
				String[] line = strLine.split("\\s+");
				if(line[0].equals("r")) {
					g2.addEdge(new Edge(map.get(map2.get((line[2]))), 
							map.get(map2.get((line[3]))), 
							distance(haversin(map2.get(line[2]).latitude), 
									haversin(map2.get(line[2]).longitude), 
									haversin(map2.get(line[3]).latitude), 
									haversin(map2.get(line[3]).longitude))));
				}
			}
			setScale();
			return g2;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	Map<Integer, Vertex> map3 = new HashMap<>();
	Map<String, Vertex> map2 = new HashMap<>();
	Map<Vertex, Integer> map = new HashMap<>();
	EdgeWeightedGraph g2 = null;

	public Edge getLowestDistance(ArrayList<Vertex> toAdd) {

		Iterable<Edge> adjacent = g2.adj(map.get(toAdd.get(0)));
		Iterator<Edge> iter = adjacent.iterator();
		Edge min = iter.next();

		while(iter.hasNext()) {
			double weight = iter.next().weight();

			if(weight < min.weight()) {

				min = iter.next();
			}
		}


		return min;
	}

	public void relax(Vertex u, Vertex v, Edge e) {
		double weight = e.weight();
		if(u.distance + weight< v.distance) {
			v.distance = u.distance + weight;
			v.previous = u;

		}
	}

	public class minComparator implements Comparator<Vertex>{

		public int compare(Vertex v1, Vertex v2) {
			if(v1.distance<v2.distance) {
				return -1;
			}
			else if(v1.distance == v2.distance) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}

	ArrayList<Vertex> path = new ArrayList<Vertex>();

	public void dijkstra(String name1, String name2) {
		int initialVertex = 0;
		int endingVertex = 0;
		try {
			initialVertex = map.get(map2.get(name1));
			endingVertex = map.get(map2.get(name2));

			minComparator minComparator = new minComparator();
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(minComparator);
			Vertex first = map3.get(initialVertex);
			first.distance = 0;
			first.previous = null;
			pq.offer(first);
			while(!pq.isEmpty()) {
				Vertex u = pq.poll();

				for(Edge e : g2.adj(map.get(u))) {

					Vertex v = map3.get(((Edge) e).other(map.get(u)));

					if(v.visited == false) {
						pq.offer(v);
						v.visited = true;
						relax(u, v, e);
					}

				}
			}
			Vertex temp = map2.get(name2);
			path.add(temp);
			if(temp.previous == null) {
				System.out.println("There is no path");
			}else {


				while(temp.previous != null) {
					path.add(temp.previous);
					temp = temp.previous;
				}
				for(int i = 0; i < path.size()/2; i++) {
					Vertex fuck = path.get(i);
					path.set(i, path.get(path.size() - 1 - i));
					path.set(path.size() - 1 - i, fuck);
				}
				System.out.println("Path: " + Arrays.asList(path));
				System.out.printf("%.2f", path.get(path.size()-1).distance);
				System.out.println(" miles");
			}
		}catch(NullPointerException e){
			System.out.println("One or both of those are not vertices");
		}


	}


	public static void main(String[] args) {
		Main main = new Main();
		String mapType = args[0];
		EdgeWeightedGraph g = main.createGraph(mapType);
		
		if(args.length >= 3) {
			if(args[2].equals("--directions")) {
				main.dijkstra(args[3], args[4]);
			}
			if(args[1].equals("--show")) {
				main.show(true);		}
		}else {
			if(args[1].equals("--show")) {
				main.show(true);
			}
		}

	}

}



