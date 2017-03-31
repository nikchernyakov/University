type Set a = a -> Bool

moreThan3 :: Set Integer
moreThan3 = flip (>) 3 -- Это Set [4..]
lessThan10 :: Set Integer
lessThan10 = flip (<) 10 -- Это Set [..10]

-- Пересечение множеств
intersect :: Set a -> Set a -> Set a
intersect set1 set2 = \el -> (set1 el) && (set2 el)
-- Объединение множеств
union :: Set a -> Set a -> Set a
union set1 set2 = \el -> (set1 el) || (set2 el)
-- Добавление элемента в множество
add :: Eq a => Set a -> a -> Set a
add set newEl = \e -> (set e) || (e == newEl)
