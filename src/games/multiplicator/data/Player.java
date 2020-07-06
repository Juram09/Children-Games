package games.multiplicator.data;

public class Player {

    private String player;
    private boolean humanPlayer;

    public Player(String player, boolean humanPlayer) {
        this.player = player;
        this.humanPlayer = humanPlayer;
    }

    public String getPlayer() {
        return player;
    }
    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.player != other.player) {
            return false;
        }
        return true;
    }
}
