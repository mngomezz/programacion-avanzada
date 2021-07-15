main :: IO ()
main = do
-- Enter your code here. Read input from STDIN. Print output to STDOUT
    x <- readLn
    print (replaceEven x)
    
replaceEven :: [Int] -> [Int]
replaceEven [] = []
replaceEven (first:tail) = if even first
 then 0:replaceEven tail
 else first:replaceEven tail