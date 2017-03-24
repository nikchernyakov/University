geron :: Double -> Double -> Double -> Double

geron a b c = let
    p = (a + b + c)/2
    in sqrt(p * (p - a) * (p - b) * (p - c))

geron' a b c = sqrt(p * (p - a) * (p - b) * (p - c)) where
    p = (a + b + c)/2