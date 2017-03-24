-- map :: (a -> b) -> [a] -> [b]

sqrtList list = map sqrt list

evenList list = map even list

sumTupleList list = map sumTuple (zip list (0 : list))
sumTuple (x,y) = x + y
