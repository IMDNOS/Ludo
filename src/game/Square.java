package game;


public class Square {


    final int id;

    final boolean isStart;
    final boolean isSpecial;
    final boolean isWinning;

    private int tokensContained = 0;
    private Role owner;


    public Square(int id, boolean isStart, boolean isSpecial, boolean isWinning) {
        this.id = id;
        this.isStart = isStart;
        this.isSpecial = isSpecial;
        this.isWinning = isWinning;
    }


    @Override
    public String toString() {

//        return "|"+id+"|";

        String output = "";

        if (isEmpty() && owner == Role.Player && isWinning)
            return "\uD83D\uDD38";
        if (isEmpty() && owner == Role.CPU && isWinning)
            return "\uD83D\uDD39";


        if (owner == Role.Player && isWall())
            return "♦\uFE0F";

        if (owner == Role.CPU && isWall())
            return "\uD83D\uDD37";

        if (owner == Role.Player)
            return "🔴";
        if (owner == Role.CPU)
            return "🔵";

        if (isStart && isEmpty())
            return "\uD83D\uDD04";

        if (isSpecial && isEmpty())
            return "☯\uFE0F";

        if (isEmpty())
            return "⬜\uFE0F";

        return output;
    }

    public void updateSquare(Player[] players) {
        tokensContained = 0;
        this.owner=null;
        for (Player player : players) {
            for (int token:player.getTokens()) {
                if (token==this.id)
                {
                    this.owner=player.role;
                    tokensContained++;
                }
            }
        }
    }

    public boolean isEmpty() {
        return getTokensContained() == 0;
    }

    public boolean isWall() {
        return getTokensContained() > 1;
    }

    public Role getOwner() {
        return owner;
    }

    public void setOwner(Role owner) {
        this.owner = owner;
    }

    public int getTokensContained() {
        return tokensContained;
    }

}
