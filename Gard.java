import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class Gard {
	
	static class Task {
		public static final String INPUT_FILE = "gard.in";
		public static final String OUTPUT_FILE = "gard.out";

		int n;
		Vector<Coordonate> v = new Vector<Coordonate>();
		private void readInput() {
			BufferedReader br = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(INPUT_FILE));
				sCurrentLine = br.readLine();
				n = Integer.parseInt(sCurrentLine);
				for (int i = 0; i < n; i++) {
					sCurrentLine = br.readLine();
					Gard g = new Gard();
					Coordonate current = g.new Coordonate();
					current.x = Integer.parseInt(sCurrentLine.split(" ")[0]);
					current.y = Integer.parseInt(sCurrentLine.split(" ")[1]);
					v.add(current);
				}
				br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private static class CoordonateComparator implements Comparator<Coordonate> {
			@Override
			public int compare(Coordonate a, Coordonate b) {
				if (a.y == b.y) {
					if (a.x < b.x) {
						return 1;
					}
					return -1;
				}
				if (a.y > b.y) {
					return 1;
				}
				return -1;
			}
		}

		private static Vector<Coordonate> getSorted(Vector<Coordonate> v) {
			Vector<Coordonate> res = new Vector<Coordonate>();
			PriorityQueue<Coordonate> p = new PriorityQueue<Coordonate>(v.size(), 
				new CoordonateComparator());
			for (Coordonate element : v) {
				p.add(element);
			}
			while (p.size() != 0) {
				res.add(p.poll());
			}
			return res;
		}

		private int getResult() {
			Vector<Coordonate> sorted = getSorted(v);
			int cnt = 0, i = 0;
			for (i = 0; i < sorted.size(); i++) {
				if (i == 0) {
					if (sorted.elementAt(i).x >= sorted.elementAt(i + 1).x 
						&& sorted.elementAt(i).y <= sorted.elementAt(i + 1).y) {
						sorted.remove(i);
						cnt++;
					}
				} else if (i == sorted.size() - 1) {
					if (sorted.elementAt(i).x >= sorted.elementAt(i - 1).x 
						&& sorted.elementAt(i).y <= sorted.elementAt(i - 1).y 
						|| sorted.elementAt(i).x <= sorted.elementAt(i - 1).x 
						&& sorted.elementAt(i).y >= sorted.elementAt(i - 1).y) {
						sorted.remove(i);
						cnt++;
					}
				} else if ((sorted.elementAt(i).x >= sorted.elementAt(i - 1).x 
					&& sorted.elementAt(i).y <= sorted.elementAt(i - 1).y) 
					|| (sorted.elementAt(i).x >= sorted.elementAt(i + 1).x 
					&& sorted.elementAt(i).y <= sorted.elementAt(i + 1).y)) {
					sorted.remove(i);
					cnt++;
					i -= 2;
				}	
			}
			return cnt;
		}
		public void solve() {
			readInput();
			writeOutput(getResult());
		}
	}
	
	class Coordonate {
		public int x;
		public int y;
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
