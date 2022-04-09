import java.util.ArrayList;

public class Point {

	public ArrayList<Point> neighbors;
	public static Integer []types ={0,1,2,3};
	public int type;
	public int staticField;
	public boolean isPedestrian;
	public boolean blocked=false;

	public Point() {
		type=0;
		staticField = 100000;
		neighbors= new ArrayList<Point>();
	}
	
	public void clear() {
		staticField = 100000;
		
	}

	public boolean calcStaticField() {
		if (type==1)
			return false;
		int min=staticField;
		boolean changed=false;
		for (Point neighbour: neighbors){
			if (neighbour.staticField+1<min){
				changed=true;
				min= neighbour.staticField+1;
			}
		}
		staticField=min;
		return changed;
	}
	
	public void move(){
		if (this.type!=3 || this.blocked)
			return;
		Point toMove=this;
		for (Point neighbour: neighbors)
			if ((neighbour.type==0 || neighbour.type==2) && neighbour.staticField< toMove.staticField)
				toMove=neighbour;
		this.type=0;
		this.isPedestrian=false;
		if (toMove.type==0) {
			toMove.type = 3;
			toMove.isPedestrian = true;
			toMove.blocked = true;
		}
	}

	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
}