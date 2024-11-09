import java.util.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int steps=0;
    static Scanner sc=new Scanner(System.in);


    public static void startGame(char arr[][],int st[],int x,int y,ArrayList<int[]> arrlist){
        System.out.println("\n\n\n\n\n");
        int m=arr.length;
        int n=arr[0].length;
        if(arr[x][y]=='E'){
            System.out.println("Level Successfully completed!!");
            return;
        }

        arrlist.add(new int[]{x,y});
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(i==x && j==y && arr[x][y]!='X'){
                    System.out.print("O ");
                }
                else{
                    System.out.print(arr[i][j]+" ");
                }

            }
            System.out.println();
        }
        System.out.println("Enter w(up)/ a(left)/ s(down)/ d(right)/ q(quit)");
        char choose=sc.next().charAt(0);
        if(choose!='w' && choose !='s' && choose !='a' && choose !='d' && choose!='q'){
            System.out.println("Please enter the correct value!!!");
            choose=sc.next().charAt(0);
        }

        if(choose=='w'){
            if(x-1<0 || arr[x-1][y]=='X'){
                startGame(arr,st,x,y,arrlist);
            }
            else{

                startGame(arr,st,x-1,y,arrlist);
            }

        }
        else if(choose=='a'){
            if(y-1<0 || arr[x][y-1]=='X'){
                startGame(arr,st,x,y,arrlist);
            }
            else{


                startGame(arr,st,x,y-1,arrlist);
            }

        }
        else if(choose=='s'){
            if(x+1>=arr.length || arr[x+1][y]=='X'){
                startGame(arr,st,x,y,arrlist);
            }
            else{
                startGame(arr,st,x+1,y,arrlist);
            }

        }
        else if(choose=='d'){
            if(y+1>=arr[0].length || arr[x][y+1]=='X'){
                startGame(arr,st,x,y,arrlist);
            }
            else{

                startGame(arr,st,x,y+1,arrlist);
            }
        }
        else if(choose=='q'){
            return;
        }
        else{
            System.out.println("Invalid Input!!!");
            startGame(arr,st,x,y,arrlist);
        }
    }


    public static void userPath(char[][]maze,ArrayList<int[]> arr,char [][]shortestMaze){

        System.out.println(arr.size());
        int userscore=0;
        int shortscore=0;

        for(int i=1;i<arr.size();i++){
            int[] e=arr.get(i);
            maze[e[0]][e[1]]='*';
        }
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                System.out.print(maze[i][j]+" ");
                if(maze[i][j]=='*')userscore++;
            }
            System.out.print("         ");
            for(int j=0;j<maze[0].length;j++){
                System.out.print(shortestMaze[i][j]+" ");
                if(shortestMaze[i][j]=='-')shortscore++;
            }
            System.out.println();
        }
        System.out.println("Congratulations!");
        System.out.println("Score: "+((shortscore*100)/userscore)+"/100");
    }

    public static void getShortestPath(char[][] maze){
        int m=maze.length;
        int moves[][]={{0,-1},{-1,0},{0,1},{1,0}};
        int n=maze[0].length;
        Queue<int[]>q=new LinkedList<>();
        int parent[][][]=new int[m][n][2];
        for(int e[][]: parent){
            for(int x[]: e){
                Arrays.fill(x,-1);
            }
        }
        q.add(new int[]{1,1});
        while(!q.isEmpty()){
            int[] x=q.poll();
            int i=x[0];
            int j=x[1];
            for(int k=0;k<moves.length;k++){
                int newi=i+moves[k][0];
                int newj=j+moves[k][1];
                if(newi<0 || newj<0 || newi>=m || newj>=n || maze[newi][newj]=='X' ||parent[newi][newj][1]!=-1)continue;
                if(maze[newi][newj]=='E') {
                    setMaze(maze,parent,i,j);
                    return;
                }
                parent[newi][newj][0]=i;
                parent[newi][newj][1]=j;
                q.add(new int[]{newi,newj});
            }
        }
    }

    public static void setMaze(char maze[][],int parent[][][],int i,int j){
        if(i==1 && j==1)return;
        maze[i][j]='-';
        setMaze(maze,parent,parent[i][j][0],parent[i][j][1]);
    }


    public static void main(String[] args) {
        System.out.println("Enter 1 to Start the Game\nEnter 2 for Exit");
        int c=sc.nextInt();
        if(c==1){
            ArrayList<int[]> arr=new ArrayList<>();
            System.out.println("Choose Level: 1(Easy), 2(Medium), 3(Hard), 4(God), 5(Exit)");
            int lev=sc.nextInt();
            if(lev==5){
                return;
            }
            while(lev!=1 && lev!=2 && lev!=3 && lev!=4){
                System.out.println("Please choose the correct value!!!");
                lev=sc.nextInt();
                if(lev==4){
                    return;
                }
            }
            char maze[][]={{}};

            char userMaze[][]={{}};
            char shortestMaze[][]={{}};
            int st[]=new int[]{0,0};
            if(lev==1){
                mazeLevel1 m=new mazeLevel1();
                mazeLevel1 x=new mazeLevel1();
                mazeLevel1 q=new mazeLevel1();
                maze=m.maze;
                userMaze=x.maze;
                shortestMaze=q.maze;
            }
            else if(lev==2){
                mazeLevel2 m=new mazeLevel2();
                mazeLevel2 x=new mazeLevel2();
                mazeLevel2 q=new mazeLevel2();
                maze=m.maze;
                userMaze=x.maze;
                shortestMaze=q.maze;
            }
            else if(lev==3){
                mazeLevel3 m=new mazeLevel3();
                mazeLevel3 x=new mazeLevel3();
                mazeLevel3 q=new mazeLevel3();
                maze=m.maze;
                userMaze=x.maze;
                shortestMaze=q.maze;
            }
            else if(lev==4){
                mazeLevelGod m=new mazeLevelGod();
                mazeLevelGod x=new mazeLevelGod();
                mazeLevelGod q=new mazeLevelGod();
                maze=m.maze;
                userMaze=x.maze;
                shortestMaze=q.maze;
            }
            else{
                return;
            }
            startGame(maze,st,1,1,arr);
            getShortestPath(shortestMaze);
            userPath(userMaze,arr,shortestMaze);
        }
        else{
            System.out.println("Game Exited");
            return;
        }
    }



}