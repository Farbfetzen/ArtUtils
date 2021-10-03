package art.farbfetzen.processingutilities;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import processing.core.PVector;

@UtilityClass
public class ChaikinsCornerCutter {

    public static List<PVector> cut(final List<PVector> corners, final float ratio, final int iterations, final boolean closed) {
        if (corners.size() < 3) {
            throw new IllegalArgumentException("corners must contain at least 3 PVectors.");
        }
        if (ratio <= 0 || ratio >= 0.5) {
            throw new IllegalArgumentException("ratio must be > 0 and < 0.5.");
        }
        if (iterations < 1) {
            throw new IllegalArgumentException("iterations must be > 0.");
        }
        return closed ? cutClosed(corners, ratio, iterations) : cutOpen(corners, ratio, iterations);
    }

    private static List<PVector> cutClosed(List<PVector> corners, final float ratio, final int iterations) {
        for (int i = 0; i < iterations; i++) {
            final List<PVector> newCorners = new ArrayList<>(corners.size() * 2);
            PVector a;
            PVector b;
            for (int k = 0; k < corners.size() - 1; k++) {
                a = corners.get(k);
                b = corners.get(k + 1);
                newCorners.add(PVector.lerp(a, b, ratio));
                newCorners.add(PVector.lerp(b, a, ratio));
            }
            a = corners.get(corners.size() - 1);
            b = corners.get(0);
            newCorners.add(PVector.lerp(a, b, ratio));
            newCorners.add(PVector.lerp(b, a, ratio));
            corners = newCorners;
        }
        return corners;
    }

    private static List<PVector> cutOpen(List<PVector> corners, final float ratio, final int iterations) {
        for (int i = 0; i < iterations; i++) {
            final List<PVector> newCorners = new ArrayList<>(corners.size() * 2 - 1);
            newCorners.add(corners.get(0));
            PVector a = corners.get(0);
            PVector b = corners.get(1);
            newCorners.add(PVector.lerp(b, a, ratio));
            for (int k = 1; k < corners.size() - 2; k++) {
                a = corners.get(k);
                b = corners.get(k + 1);
                newCorners.add(PVector.lerp(a, b, ratio));
                newCorners.add(PVector.lerp(b, a, ratio));
            }
            a = corners.get(corners.size() - 2);
            b = corners.get(corners.size() - 1);
            newCorners.add(PVector.lerp(a, b, ratio));
            newCorners.add(corners.get(corners.size() - 1));
            corners = newCorners;
        }
        return corners;
    }

}
