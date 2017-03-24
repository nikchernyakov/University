firstPrime :: Integer -> Integer
firstPrime n = firstPrime' (nextOne (n+1))

nextOne n | n `mod` 10 == 1 = n
          | otherwise = n + (11 - (n `mod` 10))

firstPrime' n | prime n = n
              | otherwise = firstPrime' (n+10)

prime n = prime' 3 n
prime' d p | d*d>p = True
           | p `mod` d == 0 = False
           | otherwise = prime' (d+2) p 