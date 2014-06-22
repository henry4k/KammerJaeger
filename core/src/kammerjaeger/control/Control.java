package kammerjaeger.control;

import com.badlogic.gdx.Gdx;
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

        final Vector2 direction = new Vector2(0,0);

        if(Gdx.input.isKeyPressed(Keys.W))
            direction.y += 1;
        if(Gdx.input.isKeyPressed(Keys.S))
            direction.y -= 1;
        if(Gdx.input.isKeyPressed(Keys.D))
            direction.x += 1;
        if(Gdx.input.isKeyPressed(Keys.A))
            direction.x -= 1;

        direction.nor(); // Normalize

        player.getBody().setLinearVelocity(direction.cpy().scl(PlayerEntity.MAX_WALK_SPEED));

        return true;
    }

    public void touch(){


    }
}
