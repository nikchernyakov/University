import java.util.List;

/**
 * Created by Никита on 09.05.2016.
 */
public class Substrings {

    private static final long q = 36028797018963913L;

    public static <E> int subsequence(List<E> whole, List<E> part) {
        int n = whole.size();   // Длина строки, в которой происходит поиск
        int m = part.size();    // Длина подстроки

        // Каждая позиция в строке образца имеет свой hash-модуль. При сдвиге по
        // исходной строке значение hash-функции вычисляется по двум символам -
        // вытесняемому символу и добавляемому символу. При этом код символа
        // умножается на hash-модуль, соответствующий позиции этого символа.
        long h = 1;               // hash-модуль вытесняемой буквы
        for (int k = 1; k <= m-1; k++) {
            h <<= 8;
            h %= q;
        }

        long p = 0;     // hash-функция подстроки - вычисляется один раз
        long t = 0;     // hash-функция очередного фрагмента подстроки
        for (int k = 0; k < m; k++) {
            p = ((p << 8) | (byte)whole.get(k).hashCode()) % q;
            t = ((t << 8) | (byte)part.get(k).hashCode()) % q;
        }

        // Внешний цикл по исходной строке
        extLoop:
        for (int i = 0; i <= n-m; i++) {
            if (p == t) {
                // hash-функции фрагмента исходной строки и образца совпали;
                // проверяем, не холостое ли это срабатывание
                for (int j = 0; j < m; j++) {
                    if (!whole.get(i+j).equals(part.get(j))) {
                        // символы не совпали - продолжаем поиск
                        continue extLoop;
                    }
                }
                // подстрока найдена!
                return i;
            } else if (i < n-m) {
                // сдвиг по исходной строке
                t = (((t - h * (byte)whole.get(i).hashCode()) << 8) | (byte)part.get(i+m).hashCode()) % q;
            }
        }
        return -1;
    }
}
