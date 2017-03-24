bitOnes :: Integer -> Int
bitOnes 0 = 0
bitOnes n = even' n + bitOnes (n `div` 2)
even' n | even n = 0
        | otherwise = 1