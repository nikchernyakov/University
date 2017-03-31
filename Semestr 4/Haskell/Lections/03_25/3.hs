-- нахождение элемента с индексом 5
fifth = flip (!!) 5 

sumLengths :: [[a]] -> Int -- Пришлось указать тип, чтобы он не ругался
sumLengths = (.) sum (map length)
-- sumLengths = sum . map length
--sumLengths lists = sum $ map length lists