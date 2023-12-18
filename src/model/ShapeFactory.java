package model;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

public class ShapeFactory {
    private static ShapeFactory shapeFactory = null;
    private Map<Class<? extends Shape>, BufferedImage[]> mappedClasses;

    public synchronized static ShapeFactory getInstance() {
        if (shapeFactory == null) {
            shapeFactory = new ShapeFactory();
        }
        return shapeFactory;
    }

    private ShapeFactory() {
        ShapeLoader shapeLoader = new ShapeLoader();
        mappedClasses = shapeLoader.loadFolder();

        if (mappedClasses.keySet().isEmpty()){
            throw new RuntimeException("Shapes cannot be empty. You should add atleast one class to the Shapes folder");
        }
    }

    public Shape getRandomShape(int x, int y) {
        Random rand = new Random();
        int shapeChoice = rand.nextInt(mappedClasses.size());
        // Generate a random index within the range of the map size
        Random random = new Random();
        int randomClassIndex = random.nextInt(mappedClasses.size());
        int randomImageIndex = random.nextInt(3);

        Class<?>[] keysArray = mappedClasses.keySet().toArray(new Class<?>[0]);

        Class<?> randomClass = keysArray[randomClassIndex];
        BufferedImage randomImage = mappedClasses.get(randomClass)[randomImageIndex];

        Shape shape = null;
        try{
            shape = (Shape) randomClass.getDeclaredConstructor(new Class[]{int.class, int.class, BufferedImage[].class, ShapeColor.class}).newInstance(new Object[]{x,y, new BufferedImage[]{randomImage},ShapeColor.RED});

        } catch(Exception e){
            System.out.println(e);
        }
        System.out.println(shape);
        return shape;
    }

    public static void main(String[] args) {
        ShapeFactory sf = ShapeFactory.getInstance();
        Shape sh = sf.getRandomShape(50, 150);

        System.out.println(sh.getSpriteImages().length);
    }
}