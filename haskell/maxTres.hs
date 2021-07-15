maxTres :: Int -> Int -> Int -> Int
maxTres x y z = maxDos z ( maxDos x y )

maxDos :: Int -> Int -> Int
maxDos x y = if x > y then x else y