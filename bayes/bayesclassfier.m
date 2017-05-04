%????
clear;
filename = 'test.csv';
delimiter = ',';
formatSpec = '%s%s%s%s%s%[^\n\r]';
fileID = fopen(filename,'r');
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);
fclose(fileID);
raw = repmat({''},length(dataArray{1}),length(dataArray)-1);
for col=1:length(dataArray)-1
    raw(1:length(dataArray{col}),col) = dataArray{col};
end
numericData = NaN(size(dataArray{1},1),size(dataArray,2));
rawNumericColumns = {};
rawCellColumns = raw(:, [1,2,3,4,5]);
age1 = rawCellColumns(:, 1);
spectacleprescrip1 = rawCellColumns(:, 2);
astigmatism1 = rawCellColumns(:, 3);
tearprodrate1 = rawCellColumns(:, 4);
contactlenses1 = rawCellColumns(:, 5);
testdata=[age1,spectacleprescrip1,astigmatism1,tearprodrate1,contactlenses1];
resultdata=testdata;


%?????????
model=importdata('bayes.model');
result=zeros(6,3);
p_hard=0.1667;
p_none=0.6250;
p_soft=0.2083;
%disp(testdata);
%disp(model);

for i=2:1:7
    %young
   if(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(1,1)*model(4,1)*model(6,1)*model(8,1)*p_none;
        result(i-1,2)=model(1,2)*model(4,2)*model(6,2)*model(8,2)*p_hard;
        result(i-1,3)=model(1,3)*model(4,3)*model(6,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(1,1)*model(4,1)*model(6,1)*model(9,1)*p_none;
        result(i-1,2)=model(1,2)*model(4,2)*model(6,2)*model(9,2)*p_hard;
        result(i-1,3)=model(1,3)*model(4,3)*model(6,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(1,1)*model(4,1)*model(7,1)*model(8,1)*p_none;
        result(i-1,2)=model(1,2)*model(4,2)*model(7,2)*model(8,2)*p_hard;
        result(i-1,3)=model(1,3)*model(4,3)*model(7,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(1,1)*model(4,1)*model(7,1)*model(9,1)*p_none;
        result(i-1,2)=model(1,2)*model(4,2)*model(7,2)*model(9,2)*p_hard;
        result(i-1,3)=model(1,3)*model(4,3)*model(7,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(1,1)*model(5,1)*model(6,1)*model(8,1)*p_none;
        result(i-1,2)=model(1,2)*model(5,2)*model(6,2)*model(8,2)*p_hard;
        result(i-1,3)=model(1,3)*model(5,3)*model(6,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(1,1)*model(5,1)*model(6,1)*model(9,1)*p_none;
        result(i-1,2)=model(1,2)*model(5,2)*model(6,2)*model(9,2)*p_hard;
        result(i-1,3)=model(1,3)*model(5,3)*model(6,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(1,1)*model(5,1)*model(7,1)*model(8,1)*p_none;
        result(i-1,2)=model(1,2)*model(5,2)*model(7,2)*model(8,2)*p_hard;
        result(i-1,3)=model(1,3)*model(5,3)*model(7,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'young')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(1,1)*model(5,1)*model(7,1)*model(9,1)*p_none;
        result(i-1,2)=model(1,2)*model(5,2)*model(7,2)*model(9,2)*p_hard;
        result(i-1,3)=model(1,3)*model(5,3)*model(7,3)*model(9,3)*p_soft;
   %ppre  
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(2,1)*model(4,1)*model(6,1)*model(8,1)*p_none;
        result(i-1,2)=model(2,2)*model(4,2)*model(6,2)*model(8,2)*p_hard;
        result(i-1,3)=model(2,3)*model(4,3)*model(6,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(2,1)*model(4,1)*model(6,1)*model(9,1)*p_none;
        result(i-1,2)=model(2,2)*model(4,2)*model(6,2)*model(9,2)*p_hard;
        result(i-1,3)=model(2,3)*model(4,3)*model(6,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(2,1)*model(4,1)*model(7,1)*model(8,1)*p_none;
        result(i-1,2)=model(2,2)*model(4,2)*model(7,2)*model(8,2)*p_hard;
        result(i-1,3)=model(2,3)*model(4,3)*model(7,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(2,1)*model(4,1)*model(7,1)*model(9,1)*p_none;
        result(i-1,2)=model(2,2)*model(4,2)*model(7,2)*model(9,2)*p_hard;
        result(i-1,3)=model(2,3)*model(4,3)*model(7,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(2,1)*model(5,1)*model(6,1)*model(8,1)*p_none;
        result(i-1,2)=model(2,2)*model(5,2)*model(6,2)*model(8,2)*p_hard;
        result(i-1,3)=model(2,3)*model(5,3)*model(6,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(2,1)*model(5,1)*model(6,1)*model(9,1)*p_none;
        result(i-1,2)=model(2,2)*model(5,2)*model(6,2)*model(9,2)*p_hard;
        result(i-1,3)=model(2,3)*model(5,3)*model(6,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(2,1)*model(5,1)*model(7,1)*model(8,1)*p_none;
        result(i-1,2)=model(2,2)*model(5,2)*model(7,2)*model(8,2)*p_hard;
        result(i-1,3)=model(2,3)*model(5,3)*model(7,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'pre-presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(2,1)*model(5,1)*model(7,1)*model(9,1)*p_none;
        result(i-1,2)=model(2,2)*model(5,2)*model(7,2)*model(9,2)*p_hard;
        result(i-1,3)=model(2,3)*model(5,3)*model(7,3)*model(9,3)*p_soft;
   %pre
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(3,1)*model(4,1)*model(6,1)*model(8,1)*p_none;
        result(i-1,2)=model(3,2)*model(4,2)*model(6,2)*model(8,2)*p_hard;
        result(i-1,3)=model(3,3)*model(4,3)*model(6,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(3,1)*model(4,1)*model(6,1)*model(9,1)*p_none;
        result(i-1,2)=model(3,2)*model(4,2)*model(6,2)*model(9,2)*p_hard;
        result(i-1,3)=model(3,3)*model(4,3)*model(6,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(3,1)*model(4,1)*model(7,1)*model(8,1)*p_none;
        result(i-1,2)=model(3,2)*model(4,2)*model(7,2)*model(8,2)*p_hard;
        result(i-1,3)=model(3,3)*model(4,3)*model(7,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'myope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(3,1)*model(4,1)*model(7,1)*model(9,1)*p_none;
        result(i-1,2)=model(3,2)*model(4,2)*model(7,2)*model(9,2)*p_hard;
        result(i-1,3)=model(3,3)*model(4,3)*model(7,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(3,1)*model(5,1)*model(6,1)*model(8,1)*p_none;
        result(i-1,2)=model(3,2)*model(5,2)*model(6,2)*model(8,2)*p_hard;
        result(i-1,3)=model(3,3)*model(5,3)*model(6,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'yes')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(3,1)*model(5,1)*model(6,1)*model(9,1)*p_none;
        result(i-1,2)=model(3,2)*model(5,2)*model(6,2)*model(9,2)*p_hard;
        result(i-1,3)=model(3,3)*model(5,3)*model(6,3)*model(9,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'hypermetrope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'reduced'))
        result(i-1,1)=model(3,1)*model(5,1)*model(7,1)*model(8,1)*p_none;
        result(i-1,2)=model(3,2)*model(5,2)*model(7,2)*model(8,2)*p_hard;
        result(i-1,3)=model(3,3)*model(5,3)*model(7,3)*model(8,3)*p_soft;
   elseif(strcmp(testdata(i,1),'presbyopic')&&strcmp(testdata(i,2),'hypermyope')&&strcmp(testdata(i,3),'no')&&strcmp(testdata(i,4),'normal'))
        result(i-1,1)=model(3,1)*model(5,1)*model(7,1)*model(9,1)*p_none;
        result(i-1,2)=model(3,2)*model(5,2)*model(7,2)*model(9,2)*p_hard;
        result(i-1,3)=model(3,3)*model(5,3)*model(7,3)*model(9,3)*p_soft;
   end
           
end
%disp(result); 

%??result?resultdata.csv
result=result';
[max_col, index] = max(result);
%disp(index);
for i=2:1:7
    if index(1,i-1)==1
        resultdata{i,5}='none';
    elseif index(1,i-1)==2
        resultdata{i,5}='hard';
    elseif index(1,i-1)==3
        resultdata{i,5}='soft';
    end
end
disp(resultdata)
FileID = fopen('result.csv', 'w'); 
for i=1:1:7
    fprintf(FileID, '%s  %s  %s   %s %s\n', resultdata{i, :}); 
end
fclose(FileID); 

