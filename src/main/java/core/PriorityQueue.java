package core;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T extends Comparable<T>> {
    // List to store the elements of the priority queue
    private final List<T> elements;

    public PriorityQueue() {
        // Initialize the elements list
        elements = new ArrayList<>();
    }

    // Add a new element to the priority queue
    public void add(T element) {
        // Add the element to the end of the list
        elements.add(element);
        // Sift the element up the tree to maintain the heap property
        siftUp(elements.size() - 1);
    }

    // Remove the element with the highest priority from the queue
    public T poll() {
        if (elements.isEmpty()) {
            // If the queue is empty, return null
            return null;
        }

        // Get the element with the highest priority
        T element = elements.get(0);
        // Replace it with the last element of the heap
        elements.set(0, elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);
        // Maintain the heap property by sifting the element down the tree
        siftDown(0);
        // Return the element with the highest priority
        return element;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    // Remove the given element from the priority queue
    public void remove(T element) {
        // Find the index of the element
        int index = elements.indexOf(element);
        if (index == -1) {
            // Element not found
            return;
        }

        // Replace the element with the last element of the heap
        elements.set(index, elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);

        // Maintain the heap property by sifting the element up or down the tree
        if (index > 0 && elements.get(index).compareTo(elements.get(getParentIndex(index))) > 0) {
            siftUp(index);
        } else {
            siftDown(index);
        }
    }

    // Sift the element at the given index up the tree to maintain the heap property
    private void siftUp(int index) {
        // Stop if we reach the root of the tree
        if (index == 0) {
            return;
        }

        // Get the parent element
        int parentIndex = getParentIndex(index);
        T parent = elements.get(parentIndex);

        // Get the current element
        T element = elements.get(index);

        // If the current element has a higher priority than the parent, swap them
        if (element.compareTo(parent) > 0) {
            swap(index, parentIndex);
            // Continue sifting up the tree
            siftUp(parentIndex);
        }
    }

    // Sift the element at the given index down the tree to maintain the heap property
    // Sift the element at the given index down the tree to maintain the heap property
    private void siftDown(int index) {
        // Get the indices of the left and right children
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        // Find the index of the element with the highest priority
        int highestPriorityIndex = index;
        if (leftChildIndex < elements.size() && elements.get(leftChildIndex).compareTo(elements.get(highestPriorityIndex)) > 0) {
            highestPriorityIndex = leftChildIndex;
        }
        if (rightChildIndex < elements.size() && elements.get(rightChildIndex).compareTo(elements.get(highestPriorityIndex)) > 0) {
            highestPriorityIndex = rightChildIndex;
        }

        // If the element with the highest priority is not the current element, swap them
        if (highestPriorityIndex != index) {
            swap(index, highestPriorityIndex);
            // Continue sifting down the tree
            siftDown(highestPriorityIndex);
        }
    }

    // Get the index of the right child element
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    // Get the index of the parent element
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    // Get the index of the left child element
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    // Swap the elements at the given indices
    private void swap(int index1, int index2) {
        T temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);
    }
}