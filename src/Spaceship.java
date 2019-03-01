import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Spaceship extends BaseActor
{
    private Thrusters thrusters;

    public Spaceship(float x, float y, Stage s)
    {
        super(x,y,s);

        loadTexture( "assets/spaceship.png" );
        setBoundaryPolygon(8);

        setAcceleration(200);
        setMaxSpeed(100);
        setDeceleration(10);

        thrusters = new Thrusters(0,0, s);
        addActor(thrusters);
        thrusters.setPosition(-thrusters.getWidth(),getHeight()/2 - thrusters.getHeight()/2 );
    }


    public void act(float dt)
    {
        super.act( dt );

        float degreesPerSecond = 120;
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            rotateBy(degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            rotateBy(-degreesPerSecond * dt);

        if (Gdx.input.isKeyPressed(Keys.UP))
        {
            accelerateAtAngle( getRotation() );
            thrusters.setVisible(true);
        }
        else
        {
            thrusters.setVisible(false);
        }

        applyPhysics(dt);

        wrapAroundWorld();
    }

}