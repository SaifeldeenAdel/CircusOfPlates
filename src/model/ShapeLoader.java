package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeLoader {
    // List to store the classes that contains the shapes to chooses randomly from when uploading
    private List<Class<? extends Shape>> loadedClass;
    // Set to store the loaded classes just making sure no duplicates
    private Set<Class<? extends Shape>> set;
    // To map between images in the file and BufferedImage objects
    private Map<Class<? extends Shape>, BufferedImage[]> map;
    // To get the name of the folder
    private File folderName;

    /*
     * Constructor
     */
    public ShapeLoader(List<Class<? extends Shape>> loadedClass, Set<Class<? extends Shape>> set,Map<Class<? extends Shape>, BufferedImage[]> map, File folderName) {
        this.loadedClass = loadedClass;
        this.set = new HashSet<>();
        this.map = map;
        this.folderName = folderName;
    }

    public void setFolderName(File folderName) {
        this.folderName = folderName;
    }

    /*
     * no use till now
     */
    public String getClassName(String fileName) {
        return fileName.replace(".java", "");
    }

    /*
     * This function takes folder name and iterates
     * through its contents either files or sub-folders
     */
//    public void loadFolder() {
//        try {
//            File[] files = folderName.listFiles();
//            for (File file : files) {
//                // If directory or folder the function is called recursively
//                if (file.isDirectory()) {
//                    setFolderName(file);  // Not sure if it works when recursive or not
//                    loadFolder();
//                    System.out.println("Folder Name: " + file.getName());
//                }
//                // If not directory the loadClass function is called and do some checkings to add the file in the list
//                else if (file.isFile() && file.getName().endsWith(".java")) {
//                    loadClass(file);
//                    System.out.println("Java File: " + file.getName());
//                }
//                // If the file is Image
//                else {
//                    loadImage(file);
//                    System.out.println("Image Name: " + file.getName());
//                }
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }

    public void loadFolder(){
        try {
            File[] files = folderName.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        setFolderName(file);
                        loadClassFiles();
                        loadImageFiles();
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /*
     * This function loads the class files(.java)
     * from the current shape folder
     */
    public void loadClassFiles() {
        File[] classFiles = folderName.listFiles(file -> file.isFile() && file.getName().endsWith(".java"));
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
        File[] imageFiles = folderName.listFiles(file -> file.isFile() && file.getName().endsWith(".png"));
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
            Class<?> cls = Class.forName(className);
            if (!set.contains(cls) && Shape.class.isAssignableFrom(cls)) {
                loadedClass.add((Class<? extends Shape>) cls);
                set.add((Class<? extends Shape>) cls);
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
            String className = getClassName(image.getName());
            Class<? extends Shape> cls = (Class<? extends Shape>) Class.forName(className);

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
}
