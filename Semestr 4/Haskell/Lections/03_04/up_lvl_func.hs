cycle [1..3]
group [1,1,2,3,4,3]
[(x,y)| x <- [1..3], y <- [3..10]]
groupBy :: (a -> a -> Bool) -> [a] -> [[a]]

bolfAreLetters :: Char -> Char -> Bool
bolfAreLetters c1 c2 = isLetter c1 && isLetter c2
filter {--(\str -> isLetter $ head str)--}(isLetter . head) $ groupBy bolfAreLetters list

