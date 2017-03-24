--e eps = e' eps 0 0

--e' eps acc n | eps > f n = r acc n
  --           | otherwise = e' eps (r acc n) (n + 1)

--r acc n = acc + f n

--f n = (1/(product [1..n]))

e eps = e' eps 1 1 1

e' eps acc n prev | eps > next n prev = r acc n prev
                  | otherwise = e' eps (r acc n (next n prev)) (n + 1) (next n prev)

r acc n prev = acc + next n prev

next n prev = prev / n