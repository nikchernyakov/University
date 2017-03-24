fib n = fib' 1 1 n

fib' a b n | n == 0 = a
           | otherwise = fib' b (a+b) (n-1)