:k - сорт типа

class Show t where -- Это тип
class Functor f where
	fmap :: (a -> b) -> f a -> f b -- здесь f - это конструктор типа
	(<$) :: b -> f a -> b
	(<$) = fmap . const
	x <$ f y = (fmap . const) x (f y)

	fmap id = id
	fmap (f . g) = (fmap f) . (fmap g)


-- Тогда Maybe это конструктор с одним элементом
instance Functor Maybe where
	fmap :: (a -> b) -> Maybe a -> Maybe b
	fmap _ Nothing = Nothing
	fmap f (Just x) = Just (f x)

instance Functor [] where
	fmap = map

data BTree t = Empty | Root (BTree t) t (BTree t)
instance Functor BTree where
	fmap _ Empty = Empty
	fmap f (Root t1 root t2) = Root (fmap f t1) (f root) (fmap f t2)

-- список из 25 нулей
replicate 25 0 
-- или так
0 <$ [1..25]

data Either a b = Left a | Right b  -- Right - если правильное значение, Left - если ошибка
index :: Int -> [a] -> Either String a
index n list | n < 0 || n >= length list = Left ("Wrong index " ++ show n)
			 | otherwise = Right (list !! n) -- !! - это элемент с индексом n в list

instance Functor (Either c) where
	fmap :: (a -> b) -> Either c a -> Either c b
	fmap _ x@(Left _) = x
	fmap f (Right x) = Right (f x)

instance Functor ((->) c) where
	fmap :: (a -> b) -> (c -> a) -> (c -> b)
	fmap f g = f . g

class Monoid a where
	mempty :: a
	mappend :: a -> a -> a