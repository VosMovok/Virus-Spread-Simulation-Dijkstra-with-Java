import java.util.*;

public class PriorityQueue {
    List<Node> values;

    public PriorityQueue() {
        this.values = new ArrayList<Node>();
    }

    // [UTILITY METHOD]
    void swap(int idx1, int idx2) {
        Node temp = values.get(idx1);
        values.set(idx1, values.get(idx2));
        values.set(idx2, temp);
    }

    // [UTILITY METHOD]
    void bubbleUp() {
        int index = values.size() - 1;
        Node element = values.get(index);

        int parentIndex;
        Node parent;

        while (index > 0) {
            parentIndex = (index - 1) / 2;
            parent = values.get(parentIndex);

            if (element.priority >= parent.priority) {
                break;
            }
            values.set(parentIndex, element);
            values.set(index, parent);

            index = parentIndex;
        }
    }

    // [UTILITY METHOD]
    void bubbleDown() {
        int index = 0;
        int length = values.size();
        Node element = values.get(0);

        int leftChildIndex, rightChildIndex;
        Node leftChild = null, rightChild;
        int swap;

        while (true) {
            leftChildIndex = (2 * index) + 1;
            rightChildIndex = (2 * index) + 2;

            swap = -1;

            if (leftChildIndex < length) {
                leftChild = values.get(leftChildIndex);

                if (leftChild.priority < element.priority) {
                    swap = leftChildIndex;
                }
            }

            if (rightChildIndex < length) {
                rightChild = values.get(rightChildIndex);

                if ((swap == -1 && rightChild.priority < element.priority)
                        || (swap != -1 && rightChild.priority < leftChild.priority)) {
                    swap = rightChildIndex;
                }
            }

            if (swap == -1) {
                break;
            }

            values.set(index, values.get(swap));
            values.set(swap, element);

            index = swap;
        }
    }

    // add to the end and bubble up the value
    void enqueue(int val, int priority) {
        Node node = new Node(val, priority);

        values.add(node);
        bubbleUp();
    }

    Node dequeue() {
        if (values.size() > 0) {
            swap(0, values.size() - 1);
            Node min = values.remove(values.size() - 1);
            bubbleDown();

            return min;
        } else {
            return null;
        }
    }
}
