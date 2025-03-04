public class TowerOfHanoi {
    public void Hanoi(int n) {
        // please enter your code here...
        // move(n, 'A', 'B', 'C');
        // System.out.println();

        String A="A";
        String B="B";
        String C="C";
        move(n,A,B,C);
    }
    public void move(int n, String fromTower, String toTower, String auxTower){
        if(n == 1){
            System.out.println("Move disk "+n+" from "+fromTower+" to "+toTower);
            return;
        }
        move(n-1, fromTower, auxTower, toTower);
        System.out.println("Move disk "+n+" from "+fromTower+" to "+toTower);
        move(n-1, auxTower, toTower, fromTower);
        //return;
    }
    
}
