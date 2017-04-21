-- Черняков Никита А3201

-- Задание:
-- Написать функцию replace :: Ord e => e -> BinHeap e -> BinHeap e,
-- которая меняет в куче минимальное значение на заданное первым аргументом новое значение и выдает модифицированную кучу.

module Homework where

import Data.List
import Data.Function

-- Во второй задаче биномиальная куча определяется как список биномиальных деревьев,
-- упорядоченный по возрастанию степеней. Как и положено в куче,
-- все потомки каждого элемента больше или равны этому элементу.
-- Аргументы типа данных BinTree представляют собой компоненты узла биномиальной кучи:
-- содержащееся в узле значение и список поддеревьев.
-- В заданных условиях во второй задаче каждого варианта требуется описать одну из функций обработки кучи.
type BinHeap a = [BinTree a]
data BinTree a = BinTree a [BinTree a]
    deriving(Show, Eq)


-- ДОПОЛНИТЕЛЬНЫЕ ФУНКЦИИ

-- Функция возвращает корень дерева
root :: BinTree e -> e
root (BinTree e _) = e

-- Сравнивает два дерева на равенство
equal :: Ord e => BinTree e -> BinTree e -> Bool
equal = on (==) root

-- Нахождение минимального элемента кучи
-- Рекурсивно сравниваем текущий элемент с минимальным элементом хвоста
-- Сравниваем деревья с помощью функции minTree
minHeapElem :: Ord e => BinHeap e -> BinTree e
minHeapElem (tree:[]) = tree
minHeapElem (tree:heap) = minTree tree (minHeapElem heap)
-- Минимум из двух деревьев
minTree :: Ord e => BinTree e -> BinTree e -> BinTree e
minTree tree1@(BinTree e1 _) tree2@(BinTree e2 _) | e1 < e2 = tree1
                                                  | otherwise = tree2


-- ОСНОВНЫЕ ФУНКЦИИ 

-- Главная функция задачи
-- Сначала находим минимальный элемент этой кучи в where
-- Записываем в minElem, чтобы не считать два раза
-- Наша композиция состоит из двух функций (2) . (1)
-- 1) Удаление из кучи минимального элемента (deleteBy equal minElem)
-- Чтобы вставить вместо него элемент с вставленным новым значением и протолкнутым вглубь
-- 2) Добавление обновленного дерева в новую кучу ((:) (replace' insertElem minElem))
-- Здесь мы сначала получаем обновленное дерево
-- Т.е. проталкиваем элемент вглубь кучи
replace :: Ord e => e -> BinHeap e -> BinHeap e
replace insertElem heap = (.) ((:) (replace' insertElem minElem)) (deleteBy equal minElem) heap
    where minElem = minHeapElem heap

-- Функция для обновления дерева после вставки значения
replace' :: Ord e => e -> BinTree e -> BinTree e 
-- Если мы дошли с нашим новым значением до листа, то просто заменяем в этом листе значение на новое
replace' insertElem (BinTree treeElem []) = BinTree insertElem []

-- Создаем новое дерево, в котором:
-- 1) В корне будет лежать либо значение нашего нового значения,
-- либо корень минимального дерева из списка детей min insertElem (root (minHeapElem nextHeap))
-- 2) Проталкиваем наше новое значение вниз по дереву replace'' insertElem nextHeap
replace' insertElem (BinTree treeElem nextHeap) = BinTree (min insertElem (root (minHeapElem nextHeap))) (replace'' insertElem nextHeap)

-- Проталкивание значения вглубь дерева
-- По сути, тоже самое, что и функция replace,
-- Но в функции replace нам по любому нужно заменить минимальное значение
-- В этой функции для того, чтобы понять нужно ли нам оставить вставляемое значение 
-- в корне дерева или обновлять дальше
-- Так, собственно и делаем
-- Проверяем, если значение вставляемого элемента больше минимального элемента кучи,
-- Выполяем функцию replace, т.е. проталкиваем дальше
-- Иначе просто возращаем исходную кучу
replace'' :: Ord e => e -> BinHeap e -> BinHeap e
replace'' insertElem heap | insertElem > ((root . minHeapElem) heap) = replace insertElem heap
                          | otherwise = heap

-- Тест 1
test1 = [(BinTree 2 []), (BinTree 13 [BinTree 14 []]), (BinTree 1 [(BinTree 5 []), (BinTree 6 [(BinTree 8 [])])])]
result1 = [BinTree 5 [BinTree 7 [],BinTree 6 [BinTree 8 []]],BinTree 2 [],BinTree 13 [BinTree 14 []]]

-- Тест 2
test2 = [BinTree 3 [BinTree 6 [BinTree 8 []], BinTree 5 [BinTree 9 [], BinTree 7 [BinTree 10 []]]]]
result2 = [BinTree 5 [BinTree 7 [BinTree 8 [BinTree 10 []],BinTree 9 []],BinTree 6 [BinTree 8 []]]]

-- Хотя здесь, я не уверен, что он правильно сравнивает результаты, но в тестах приведено все подробно
main = [
    (replace 7 test1) == result1,
    (replace 8 test2) == result2
    ]
