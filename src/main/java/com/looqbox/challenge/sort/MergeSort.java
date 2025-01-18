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

    public List<String> sort(List<String> list, SortType sortType) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<String> left = sort(new ArrayList<>(list.subList(0, mid)), sortType);
        List<String> right = sort(new ArrayList<>(list.subList(mid, list.size())), sortType);

        return merge(left, right, sortType);
    }

    private List<String> merge(List<String> left, List<String> right, SortType sortType) {
        List<String> merged = new ArrayList<>();
        int leftIndex = 0, rightIndex = 0;

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

            if (condition) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }

        merged.addAll(left.subList(leftIndex, left.size()));
        merged.addAll(right.subList(rightIndex, right.size()));

        return merged;
    }
}
