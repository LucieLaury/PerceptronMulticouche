package knn;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Imagette {
    private int[][] pixels;
    private int etiquette;

    public Imagette(int numRows, int numCols) {
        pixels = new int[numRows][numCols];
    }

    // GETTERS ET SETTERS
    public void setPixel(int row, int col, int value) {
        pixels[row][col] = value;
    }

    public void setEtiquette(int i) { this.etiquette = i; }

    public int getPixel(int row, int col) {
        return pixels[row][col];
    }

    public int getWidth() {
        return pixels[0].length;
    }

    public int getHeight() {
        return pixels.length;
    }

    public int getEtiquette() {
        return etiquette;
    }

    // Permet de charger une imagette
    public static Imagette[] chargerImagettes(String nomFichierI,String nomFichierE) {
        try (FileInputStream fichier = new FileInputStream(nomFichierI);
             DataInputStream lecteur = new DataInputStream(fichier)) {

            // Lire l'en-tête du fichier
            int type = lecteur.readInt(); // Charge le type de l'image
            int numImages = lecteur.readInt(); // Charge le nombre d'images
            int numRows = lecteur.readInt();  // Charge le nombre de lignes
            int numCols = lecteur.readInt();  // Charge le nombre de colonnes

            // Tableau retour d'imagettes
            Imagette[] imagettes = new Imagette[numImages];

            // Liste d'étiquettes
            int[] etiquettes = listeEtiquettes(nomFichierE);

            // Lire les imagettes
            for (int i = 0; i < numImages; i++) {
                Imagette imagette = new Imagette(numRows, numCols);
                imagette.setEtiquette(etiquettes[i]);
                // Pour chaque ligne
                for (int row = 0; row < numRows; row++) {
                    // Pour chaque colonne
                    for (int col = 0; col < numCols; col++) {
                        // Lire et ajouter le pixel
                        int pixelValue = lecteur.readUnsignedByte();
                        imagette.setPixel(row, col, pixelValue);
                    }
                }
                // Ajout de l'imagette au tableau retour
                imagettes[i] = imagette;
            }

            return imagettes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sauvegarderImages(Imagette[] imagettes) {
        for (int i = 0; i < imagettes.length; i++) {
            Imagette imagette = imagettes[i];
            BufferedImage image = new BufferedImage(imagette.getWidth(), imagette.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

            for (int row = 0; row < imagette.getHeight(); row++) {
                for (int col = 0; col < imagette.getWidth(); col++) {
                    int pixelValue = imagette.getPixel(row, col);
                    int rgb = new Color(pixelValue,pixelValue,pixelValue).getRGB();
                    image.setRGB(col, row, rgb);
                }
            }

            try {
                String nomFichier = "imagettes/imagette_" + i + ".jpg";
                ImageIO.write(image, "JPG", new File(nomFichier));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int[] listeEtiquettes(String nomFichier) {
        try (FileInputStream fichier = new FileInputStream(nomFichier);
             DataInputStream lecteur = new DataInputStream(fichier)) {

            // Lire l'en-tête du fichier
            int type = lecteur.readInt(); // Charge le type de l'image
            int numEtiquettes = lecteur.readInt(); // Charge le nombre d'etiquettes

            // Tableau retour d'étiquettes
            int[] etiquettes = new int[numEtiquettes];

            for (int i = 0; i<numEtiquettes;i++) {
                etiquettes[i] = lecteur.readUnsignedByte();
            }

            return etiquettes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        File directory = new File("imagettes");
        if (!directory.exists()) {
            directory.mkdir(); // Crée le répertoire s'il n'existe pas
        }
        String nomFichier = "";
        Imagette[] imagettes = new Imagette[0];
        imagettes = chargerImagettes("files/train-images.idx3-ubyte","files/train-labels.idx1-ubyte");
        sauvegarderImages(imagettes);
        //Donnees donnees = new Donnees(imagettes);
        //PlusProche pp = new PlusProche(donnees);
        imagettes = chargerImagettes("files/t10k-images.idx3-ubyte","files/t10k-labels.idx1-ubyte");
        sauvegarderImages(imagettes);
        //Statistiques.stats(pp,new Donnees(imagettes));
    }
}

