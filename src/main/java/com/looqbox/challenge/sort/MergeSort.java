package com.looqbox.challenge.sort;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MergeSort {

    public enum SortType {
        ALPHABETICAL,
        LENGTH
    }

    // Main sorting method
    public List<String> sort(List<String> list, SortType sortType) {
        // Base case: If the list has one or no elements, it's already sorted
        if (list.size() <= 1) {
            return list;
        }

        // Divide the list into two halves: left and right
        int mid = list.size() / 2;
        // Recursively sort the left and right halves
        List<String> left = sort(new ArrayList<>(list.subList(0, mid)), sortType);
        List<String> right = sort(new ArrayList<>(list.subList(mid, list.size())), sortType);

        // Merge the two sorted halves and return the result
        return merge(left, right, sortType);
    }

    // Merge the two sorted lists into a single sorted list
    private List<String> merge(List<String> left, List<String> right, SortType sortType) {
        List<String> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

        // Compare elements from both lists and merge them into the result list
        while (leftIndex < left.size() && rightIndex < right.size()) {
            boolean condition;
            switch (sortType) {
                case LENGTH:
                    condition = left.get(leftIndex).length() <= right.get(rightIndex).length();
                    break;
                case ALPHABETICAL:
                default:
                    condition = left.get(leftIndex).compareToIgnoreCase(right.get(rightIndex)) <= 0;
                    break;
            }

            // Add the element to the merged list based on the condition
            if (condition) {
                merged.add(left.get(leftIndex++)); // Take element from left list
            } else {
                merged.add(right.get(rightIndex++)); // Take element from right list
            }
        }

        // Add any remaining elements from left or right list (one of them might still have elements)
        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));

        return merged;
    }
}
