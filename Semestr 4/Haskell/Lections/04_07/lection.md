## Примеры крутых задач на Haskell ##
__Задача:__ Найти кратчайшие пути в графе  
Программа состоит из двух модулей
1) Модуль граф
2) Модуль с алгоритмом Бэлмана-Форда

## Модуль граф ## 
```haskell
module Graph(Graph, Arc, verts, arcs, from, to, weight,
 createGraph, addArc, gmap, gfold) where  -- конструкторы в скобках не указаны, поэтому их публично не использовать

 newtype Graph w = Graph [[(w, Int)]] deriving Show
 newtype Arc w = Arc (Int, w, Int)

 verts :: Graph w -> Int
 verts (Graph list) = length list

 arcs :: Graph w -> [Arc w]
 arcs g@(Graph list) = concat $ zipWith addStart [0..n-1] list
 	where n = verts g
 		addStart u list = map (\(w, v) -> Arc (u, w, v)) list

 from :: Arc w -> Int
 from (Arc (u, _, _)) = u

 to :: Arc w -> Int
 to (Arc (_, _, v)) = v

 weight :: Arc w -> Int
 weight (Arc (_, w, _)) = w

 createGraph :: Int -> Graph w
 createGraph n = Graph $ replicate n []

 addArc :: (Int, w, Int) -> Graph w -> Graph w
 addArc (from, w, to) (Graph list)
 		| from < 0 || to < 0 || from >= n || to >= n = error "Vertex does not exist"
 		| otherwise = Graph $ prev ++ (((w, to) : arcs) : next)
 	where n = length list
 		(prev, arcs:next) = splitAt from list

 gmap :: (v -> w) Graph v -> Graph w
 gmap f (Graph list) = Graph $ map mapFunc list
 	where mapFunc arcs = map (\(w, v) -> (f w, v)) arcs

 gfold :: (b -> Arc w -> b) -> b -> Graph w -> b
 gfold f seed g = foldl f seed $ arcs g
```

## Модуль алгоритма Бэлмана-Форда ##
```haskell 
module BellmenFordMaybe where
import Graph
import Data.Maybe

type ArcList w = [Arc w]
type Weight w = (Int -> Maybe w)

initDistance :: Num w => Int -> Graph w -> Weight w
initDistance u gr = replace u 0 (\v -> Nothing) -- Зачем нам gr????

distances :: Graph w -> Weight w -> [Maybe w]
distances g weights = map weights [0..verts g - 1]

replace :: Int -> w -> Weight w -> Weight w
replace u new weigths = \v -> if u ==v then Just new else weights v

relax :: Real w => Weights w -> Arc w -> (Bool, Weight w)
relax weights arc = 
		if isJust wFrom && 
			(isNothing wTo || fromJust wFrom + w < fromJust wTo) then
				(True, replace t (fromJust wFrom + w) weights) else
				(False, weights)
	where f = from arc
		  t = to arc
		  w = weight arc
		  wFrom = weights f
		  wTo = weights t

bfCycle :: Real w => Weight w -> Graph w -> (Bool, Weight w)
bfCycle weights g = gfold func (False, weights) g
	where func (ch, weight) arc = (ch || newCh, newWeight)
		where (newCh, newWeight) = relax weight arc

bellmanFord :: (Real w, Show w) => Int -> Graph w -> [Maybe w]
bellmanFord start gr = if changed then [] else distances gr fina
	where (changed, _, final) = steps (0, initDistance start gr)
		  steps (k, weights) | k > n = (True, n, weights)
		  				     | ch = steps (k + 1, newWeights)
						     | otherwise = (False, k, weights)
			where (ch, newWeights) = bfCycle weights gr
		n = verts gr
testGraph :: Graph Int
testGraph = 
	addArc (0,2,1)  $ addArc (0,2,2) $
	addArc (1,-1,2) $ addArc (1,2,3) $
	addArc (0,2,1) $ addArc (2,2,4) $
	addArc (0,2,1) $ addArc (0,2,2) $
	addArc (0,2,1) $ addArc (0,2,2) $
	addArc (0,2,1) $ addArc (0,2,2) $
--- Зря писал, код будет на сайте
```