package art.farbfetzen.artutils.delaunay;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import art.farbfetzen.artutils.Vector2;

/**
 * Like a QuadTree but for triangles.
 */
class TriangleTree {

    @RequiredArgsConstructor
    private static class TriangleNode {

        private final Triangle self;
        private final TriangleNode parent;
        private TriangleNode child1;
        private TriangleNode child2;
        private TriangleNode child3;

        boolean insert(final Vector2 point) {
            if (self.containsPoint(point)) {
                if (child1 == null) {
                    child1 = new TriangleNode(new Triangle(self.getA(), self.getB(), point), this);
                    child2 = new TriangleNode(new Triangle(self.getB(), self.getC(), point), this);
                    child3 = new TriangleNode(new Triangle(self.getC(), self.getA(), point), this);
                    return true;
                }
                return child1.insert(point) || child2.insert(point) || child3.insert(point);
            }
            return false;
        }

        void getLeafNodes(final List<Triangle> leaves) {
            if (child1 == null) {
                leaves.add(self);
            } else {
                child1.getLeafNodes(leaves);
                child2.getLeafNodes(leaves);
                child3.getLeafNodes(leaves);
            }
        }

    }

    @Getter
    private final TriangleNode root;

    TriangleTree(final Triangle root) {
        this.root = new TriangleNode(root, null);
    }

    boolean insert(final Vector2 point) {
        return root.insert(point);
    }

    List<Triangle> getLeafNodes() {
        final List<Triangle> leafNodes = new ArrayList<>();
        root.getLeafNodes(leafNodes);
        return leafNodes;
    }

}
