import com.badlogic.gdx.Input.Keys;

public class LevelScreen extends BaseScreen
{
    private Spaceship spaceship;

    @Override
    public void initialize()
    {
        BaseActor space = new BaseActor(0,0, mainStage);
        space.loadTexture("assets/space.png");
        space.setSize(800,600);
        BaseActor.setWorldBounds(space);

        spaceship = new Spaceship(400,300, mainStage);

        new Asteroid(600,500, mainStage);
        new Asteroid(600,300, mainStage);
        new Asteroid(600,100, mainStage);
        new Asteroid(400,100, mainStage);
        new Asteroid(200,100, mainStage);
        new Asteroid(200,300, mainStage);
        new Asteroid(200,500, mainStage);
        new Asteroid(400,500, mainStage);
    }

    @Override
    public void update(float dt)
    {
        for (BaseActor asteroidActor : BaseActor.getList(mainStage, "Asteroid"))
        {
            if(asteroidActor.overlaps(spaceship))
            {
                if(spaceship.shieldPower <= 0)
                {
                    Explosion boom = new Explosion(0,0,mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000,-1000);
                }
                else
                {
                    spaceship.shieldPower -=34;
                    Explosion boom = new Explosion(0,0,mainStage);
                    boom.centerAtActor(asteroidActor);
                    asteroidActor.remove();
                }
            }

            for ( BaseActor laserActor : BaseActor.getList(mainStage, "Laser") )
            {
                if (laserActor.overlaps(asteroidActor))
                {
                    Explosion boom = new Explosion(0,0, mainStage);
                    boom.centerAtActor(asteroidActor);
                    laserActor.remove();
                    asteroidActor.remove();
                }
            }
        }
    }

    @Override
    public boolean keyDown(int keycode)
    {
        if( keycode == Keys.X)
            spaceship.warp();
        if (keycode == Keys.SPACE)
            spaceship.shoot();

        return false;
    }
}
