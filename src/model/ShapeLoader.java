package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeLoader {
    // To map between images in the file and BufferedImage objects
    private Map<Class<? extends Shape>, BufferedImage[]> map;
    // To get the name of the folder
    private File folder;
    private String currentLoading;

    public void setCurrentLoading(String currentLoading) {
        this.currentLoading = currentLoading;
    }

    /*
     * Constructor
     */
    public ShapeLoader() {
        this.map = new HashMap<>();
        this.folder = new File("src/model/Shapes");
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }

    /*
     * no use till now
     */
    public String getClassName(String fileName) {
        setCurrentLoading(fileName.replace(".java", ""));
        return currentLoading;
    }


    public Map<Class<? extends Shape>, BufferedImage[]> loadFolder(){
        try {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        setFolder(file);
                        loadClassFiles();
                        loadImageFiles();
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return this.map;
    }

    /*
     * This function loads the class files(.java)
     * from the current shape folder
     */
    public void loadClassFiles() {
        File[] classFiles = folder.listFiles(file -> file.isFile() && file.getName().endsWith(".java"));
        if (classFiles != null) {
            for (File classFile : classFiles) {
                loadClass(classFile);
            }
        }
    }

    /*
     * This function loads the image files(.png)
     * from the current shape folder
     */
    public void loadImageFiles() {
        File[] imageFiles = folder.listFiles(file -> file.isFile() && file.getName().endsWith(".png"));
        if (imageFiles != null) {
            for (File imageFile : imageFiles) {
                loadImage(imageFile);
            }
        }
    }

    /*
     * This function adds the .java file but checks if the file
     * does not have duplicates and that it implements Shape interface
     */
    public void loadClass(File file) {
        try {
            String className = getClassName(file.getName());
            Class<?> cls = Class.forName("model.Shapes." + className + "." + className);
            if (!map.containsKey(cls) && Shape.class.isAssignableFrom(cls)) {
                map.put((Class<? extends Shape>) cls, new BufferedImage[0]); // Initialize the array for images
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * This function stores the images in BufferedImage
     */
    public void loadImage(File image) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image);
            Class<? extends Shape> cls = (Class<? extends Shape>) Class.forName("model.Shapes." + currentLoading + "." + currentLoading);
            if (map.containsKey(cls)) {
                BufferedImage[] images = map.get(cls);
                map.put(cls, Arrays.copyOf(images, images.length + 1));
                map.get(cls)[images.length] = bufferedImage;
            } else {
                map.put(cls, new BufferedImage[]{bufferedImage});
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ShapeLoader loader = new ShapeLoader();
        Map<Class<? extends Shape>, BufferedImage[]> loadedMap = loader.loadFolder();
        System.out.println(loadedMap.keySet());
    }
}
