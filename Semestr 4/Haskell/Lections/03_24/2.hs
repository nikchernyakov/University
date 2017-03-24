data Ordering = LT | EQ | GT

instance Monoid Ordering where
	mempty :: EQ
	LT `mappend` _ = LT
	GT `mappend` _ = GT
	EQ `mappend` x = x

myCompare :: (Ord a, Ord a1) => (a1, a) -> (a1, a) -> Ordering
myCompare (x1, x2) (y1, y2) = (compare x2 y2) `mappend` (compare x1 y1)

class Functor f => Applicative f where
	pure :: a -> f a
	(<*>) :: 