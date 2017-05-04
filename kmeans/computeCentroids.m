%重新计算每个集群中所有数据点的平均值，并将计算出的平均值分配为集群的新质心。
function centroids = computeCentroids(X, idx, K)

  [m n] = size(X);
  centroids = zeros(K, n);
  
  for i=1:K
    xi = X(idx==i,:);
    ck = size(xi,1);
    centroids(i, :) = (1/ck) * sum(xi);
  end
end