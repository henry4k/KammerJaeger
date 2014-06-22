package kammerjaeger.control;

import com.badlogic.gdx.math.Vector2;
import kammerjaeger.entity.PlayerEntity;

import static com.badlogic.gdx.Input.Keys;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class Control {

    float x = 0;
    float y = 0;
   Vector2 playerDirection;

    public void gamepad(){


    }

    public boolean keyboard(int keycode, String Keypush, PlayerEntity player){

        playerDirection = new Vector2(x,y);
        player.getBody().setLinearVelocity(playerDirection.scl(500f));
            if(Keypush == "DOWN") {

                if(keycode == Keys.W) {
                    y += 1f;
                }
                if(keycode == Keys.A) {
                    x += -1f;
                }
                if(keycode == Keys.S) {
                    y += -1f;
                }
                if(keycode == Keys.D) {
                    x += 1f;
                }

                return true;
            }


    if(Keypush == "UP")
            {
                if(keycode == Keys.W) {
                    y -= 1f;
                }
                if(keycode == Keys.A) {
                    x -= -1f;
                }
                if(keycode == Keys.S) {
                    y -= -1f;
                }
                if(keycode == Keys.D) {
                    x -= 1f;
                }
                return true;
            }

        return true;
    }

    public void touch(){


    }
}
