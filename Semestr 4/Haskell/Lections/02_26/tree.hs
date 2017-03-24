data Point a = Pt a a

data Tree a = Tr a (Tree a) (Tree a) | Nil

-- Поиск корня
root (Tr a _ _) = a
height :: Tree a -> Int
height Nil = 0
height (Tr _ left right) = max (height left) (height right) + 1

-- data IntList = Constr Int IntList | Nil
