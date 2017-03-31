myFunc :: Int -> Int -> Int -> Int
myFunc x y z = x * y + z
myFunc = \x -> \y -> \z -> (x * y + z) -- тоже самое, что выше

doubleList list = map (2*) list -- list можно убрать
-- как это представляется
doubleList = \list -> map (2*) list -- также list можнр убрать

map (flip (-) 2)

sumLengths lists = sum $ map length lists

-- для кр вспомнит