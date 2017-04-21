-- Черняков Никита А3201

-- Задача:
-- Написать функцию listWords :: Dictionary -> [String],
-- которая выдает список всех слов в заданном словаре.
module Homework where

data Trie = Empty | Node Char [Trie]
type Dictionary = [Trie]

-- listWords - функция для нахождения всех слов в списке
-- Аргументом передается Dictionary
-- Функция делает следующее:
-- 1) Бежит по каждому элементу с помощью foldr
-- 2) Применяет сначала к элементу Trie дополнительную функцию listWords'
-- (Находит постфиксы каждого слова, которые хранит текущая Trie)
-- 3) Все получившиеся слова склеиваются в один лист с помощью свертки с (++)
-- (Аккумулятором выступает пустой список)
listWords :: Dictionary -> [String]
listWords = foldr ((++) . listWords') []

-- listWords' - доп. функция, которая находит постфиксы слов из бора и дописывает текущую букву к каждому постфиксу
-- 1) Вызываем функцию listWords для нахождения всех постфиксов
-- 2) С помощью map добавляем к каждому постфиксу
listWords' :: Trie -> [String]
listWords' Empty = [""]
listWords' (Node root tries) = map ((:) root) $ listWords tries

removeWord :: String -> Dictionary -> Dictionary
removeWord (c:cs) = map ()


test1 = [Node 'b' [Node 'i' [Node 't' [Empty, Node 'e' [Empty]]], Node 'y' [Node 't' [Node 'e' [Empty]]]], Node 's' [Node 'i' [Node 't' [Node 'e' [Empty]]]]]
test2 = [Node 'h' [Node 'a' [Node 's' [Node 'k' [Empty, Node 'e' [Node 'l' [Empty]]], Node 'a' [Node 'n' [Empty]]], Node 'm' [Node 's' [Node 't' [Node 'e' [Node 'r' [Empty]]]], Empty]]]]
test3 = []

main = [
    listWords test1 == ["bit","bite","byte","site"], 
    listWords test2 == ["hask","haskel","hasan","hamster","ham"],
    listWords test3 == []
    ]