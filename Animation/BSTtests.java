package pomPomTree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTtests {

    BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() throws Exception {
        tree = new BinarySearchTree<>();
    }

    @Test
    void test_insert() {
        tree.insert(5);
        assertTrue(tree.isThere(5));
    }

    @Test
    void test_delete() {
        tree.insert(5);
        tree.delete(5);
        assertFalse(tree.isThere(5));
    }

    @Test
    void test_retrieve() {
        tree.insert(5);
        assertEquals(5, tree.retrieve(5));
    }

    @Test
    void test_clear() {
        tree.insert(5);
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    void test_isEmpty() {
        assertTrue(tree.isEmpty());
        tree.insert(5);
        assertFalse(tree.isEmpty());
    }

    @Test
    void test_numberOfNodes() {
        assertEquals(0, tree.numberOfNodes());
        tree.insert(5);
        tree.insert(10);
        assertEquals(2, tree.numberOfNodes());
    }
}
