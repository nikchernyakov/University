class Monoid a where
	mempty :: a
	mappend :: a -> a -> a
	mconcat :: [a] -> a
	mconcat = foldr mempty mappend

	mappend mempty x = x
	mappend x mempty = x
	mappend x (mappend y z) = mappend (mappend x y) z

class Foldable t where
	foldr :: (a -> b -> b) -> b -> t a -> b
	foldMap :: Monoid m => (a -> m) -> t a -> m

instance Foldable BTree where
	foldMap _ Empty = mempty
	foldMap f (Root tl root tr) = (foldMap f tl) `mappend` (f root) `mappend` (foldMap f tr)

flatten :: BTree a -> [a]
flatten = foldr (++) []

