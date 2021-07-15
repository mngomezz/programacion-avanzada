{-# OPTIONS_GHC -Wno-incomplete-patterns #-}
-- apilar 
push :: [Int] -> Int -> [Int]
push [] top = [top]
push stack top = top:stack

-- desapilar
pop :: [Int] -> Int
pop (top:estack) = top

-- encolar
enqueue :: [Int] -> Int -> [Int]
enqueue queue last = queue++[last]

-- desencolar
queue :: [Int] -> Int
queue (first:queue) = first