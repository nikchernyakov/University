palindrom :: Integer -> Bool
palindrom n = list == reverse list
    where list = toList n 

toList :: Integer -> [Integer]
toList n = toList' n []

toList' 0 list = list
toList' n list = toList' (n `div` 10) ((n `mod ` 10):list)