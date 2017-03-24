class Sort {

    public static <E extends Comparable<? super E>> void quickSort(E[] array) {
        quickSort(array, 0, array.length);
    }

    private static <E extends Comparable<? super E>> void quickSort(E[] array, int begin, int end) {
        if (end - begin <= 1) {
            return;
        }

        E med = array[(begin + end) / 2];
        int i = begin;
        int j = end - 1;
        while (j > i) {
            while (i < j && array[i].compareTo(med) < 0) {
                i++;
            }
            while (j > i && array[j].compareTo(med) > 0) {
                j--;
            }
            if (i < j) {
                if (array[i].compareTo(array[j]) == 0) {
                    i++;
                } else {
                    E tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }

        quickSort(array, begin, i);
        quickSort(array, i, end);
    }

    public static <E extends Comparable<? super E>> void mergeSort(E[] array) {
        mergeSort(array, 0, array.length, new Object[array.length]);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Comparable<? super E>> void mergeSort(E[] array, int begin, int end, Object[] tmp) {
        if (end - begin <= 1) {
            return;
        }

        int med = (begin + end) / 2;
        mergeSort(array, begin, med, tmp);
        mergeSort(array, med, end, tmp);

        for (int i = begin, j = med, k = 0; k < end - begin; k++) {
            if (i == med) {
                while (k < end - begin) {
                    tmp[k++] = array[j++];
                }
            } else if (j == end) {
                while (k < end - begin) {
                    tmp[k++] = array[i++];
                }
            } else if (array[i].compareTo(array[j]) <= 0) {
                tmp[k] = array[i++];
            } else {
                tmp[k] = array[j++];
            }
        }

        for (int i = begin; i < end; i++) {
            array[i] = (E) tmp[i - begin];
        }
    }

    public static <E extends Comparable<? super E>> void bubbleSort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean wasPerm = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) >= 0) {
                    E tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    wasPerm = true;
                }
            }
            if (!wasPerm) {
                break;
            }
        }
    }
}
