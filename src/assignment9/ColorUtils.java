package assignment9;

import java.awt.Color;

public class ColorUtils {
    
    public static final Color[] PASTEL = {
        new Color(255, 182, 193), // Light Pink
        new Color(176, 224, 230), // Powder Blue
        new Color(152, 251, 152), // Pale Green
        new Color(221, 160, 221), // Plum
        new Color(255, 239, 213), // Papaya Whip
        new Color(255, 228, 225), // Misty Rose
        new Color(240, 230, 140)  // Khaki (soft yellow)
    };

    private static int currentColorIndex = 0; // To cycle through pastel colors

    public static Color getNextColor() {
        Color color = PASTEL[currentColorIndex];
        // Update to the next color in the cycle
        currentColorIndex = (currentColorIndex + 1) % PASTEL.length;
        return color;
    }

    public static Color solidColor() {
        return getNextColor(); // Always return the next color in the cycle
    }

    private static Color transparent(Color c) {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        return new Color(r, g, b, 64); // Alpha 64 for transparency
    }

    public static Color transparentColor() {
        return transparent(solidColor());
    }
}




