data BTree t = Empty | Root t (BTree t) (BTree t)
--    deriving Eq  -- Если не переопределять, можно написать это

-- class - описывает интерфейс
class Eq t where  
    (==), (/=) :: t -> t -> Bool
    t1 /= t2 = not (t1 == t2)  -- В реализации интерфейса это уже можно тогда не писать
    t1 == t2 = not (t1 /= t2)

-- instance - реализация интерфейса class
instance Eq t => Eq (BTree t) where
    -- Определение для ==
    Empty == Empty = True
    Root a t1 t2 == Root b t11 t21 = a == b && t1 == t11 && t2 == t21
    _ == _ = False

    -- Определение для /=
    t1 /= t2 = not (t1 == t2)


class Eq t => Ord t where
    (<), (>), (<=), (>=) :: t -> t -> Bool
    a <= b = (a < b) || (a == b)
    a < b = not (a >= b)
    a >= b = (a > b) || (a == b)
    a > b = not (a <= b)


data Maybe t = Nothing | Just t

fromJust -- Вытаскивает из Just

maybe (-1) id $ fromIndex -- Сама вытаскивает из Just