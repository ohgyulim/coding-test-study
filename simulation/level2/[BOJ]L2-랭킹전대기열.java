import java.util.*;
import java.io.*;

class Room{

}

class Main{
    static class Player{
        int level;
        String nickName;
        public Player(int level, String nickName){
            this.level = level;
            this.nickName = nickName;
        }
    }

    static class Room{
        int baseLevel;
        List<Player> players = new ArrayList();
        public Room(int level){
            this.baseLevel = level;
        }

        public boolean canEnter(Player p, int max){
            return (max > players.size() && Math.abs(p.level - baseLevel) <= 10);
        }
    }


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList();

        for(int i = 0; i < p; i++){
            String[] str = br.readLine().split(" ");
            Player player = new Player(Integer.parseInt(str[0]), str[1]);
            boolean isEntered = false;

            for (Room room : rooms) {
                if (room.canEnter(player, m)) {
                    room.players.add(player);
                    isEntered = true;
                    break;
                }
            }
            if (!isEntered) {
                Room newRoom = new Room(player.level);
                newRoom.players.add(player);
                rooms.add(newRoom);
            }

        }

        for(Room r : rooms){
            if(r.players.size() == m) System.out.println("Started!");
            else System.out.println("Waiting!");

            r.players.sort(Comparator.comparing(p1 -> p1.nickName));
            for(Player p1 : r.players){
                System.out.println(p1.level + " " + p1.nickName);
            }
        }

    }
}