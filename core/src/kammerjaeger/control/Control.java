package kammerjaeger.control;

import com.badlogic.gdx.math.Vector2;
import kammerjaeger.KammerJaegerGame;
import kammerjaeger.entity.Entity;
import kammerjaeger.entity.PlayerEntity;

import static com.badlogic.gdx.Input.Keys;

/**
 * Created by Sven on 22.06.2014.
 * Im the King of the Hill
 */
public class Control {



    public void gamepad(){


    }

    public boolean keyboard(int keycode, String Keypush, PlayerEntity player){


            if(Keypush == "DOWN") {
                switch (keycode) {
                    case Keys.W:
                            //player.getBody().applyForce(new Vector2(0,500), new Vector2(0,0), true);
                        player.getBody().setLinearVelocity(0.0f, 500.0f);
                        break;
                    case Keys.A:
                        player.getBody().setLinearVelocity(-500.0f,0.0f);
                        break;
                    case Keys.S:
                        player.getBody().setLinearVelocity(0.0f, -500.0f);
                        break;
                    case Keys.D:
                        player.getBody().setLinearVelocity(500.0f, 0.0f);
                        break;
                }
                return true;
            }

            if(Keypush == "UP")
            {
                switch (keycode)
                {
                    case Keys.W:
                        player.getBody().setLinearVelocity(0.0f, 0.0f);

                        break;
                    case Keys.A:
                        player.getBody().setLinearVelocity(0.0f, 0.0f);
                        break;
                    case Keys.S:
                        player.getBody().setLinearVelocity(0.0f, 0.0f);
                        break;
                    case Keys.D:
                        player.getBody().setLinearVelocity(0.0f, 0.0f);
                        break;
                }
                return true;
            }

        return false;
    }

    public void touch(){


    }
}
