%??????
clear;
filename = 'train.csv';
delimiter = ',';
formatSpec = '%s%s%s%s%s%[^\n\r]';
fileID = fopen(filename,'r');
dataArray = textscan(fileID, formatSpec, 'Delimiter', delimiter,  'ReturnOnError', false);
fclose(fileID);
age = dataArray{:, 1};
spectacleprescrip = dataArray{:, 2};
astigmatism = dataArray{:, 3};
tearprodrate = dataArray{:, 4};
contactlenses = dataArray{:, 5};
clearvars filename delimiter formatSpec fileID dataArray ans;
traindata=[age,spectacleprescrip,astigmatism,tearprodrate,contactlenses];


%???????????????
num_none=0;
num_hard=0;
num_soft=0;
N=25;
for i=2:1:N
    if(strcmp(traindata(i,5),'none'))
        num_none=num_none+1;
    elseif(strcmp(traindata(i,5),'hard'))
        num_hard=num_hard+1;
    else
        num_soft=num_soft+1;
    end
end
p_none=num_none/24;
p_hard=num_hard/24;
p_soft=num_soft/24;

%???????????????????????model?
model=zeros(9,3);
for i=2:1:N
    %??age
    if(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,1),'young'))
       model(1,1)=model(1,1)+1;
    elseif(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,1),'pre-presbyopic'))
       model(2,1)=model(2,1)+1;
    elseif(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,1),'presbyopic'))
        model(3,1)=model(3,1)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,1),'young'))
        model(1,2)=model(1,2)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,1),'pre-presbyopic'))
        model(2,2)=model(2,2)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,1),'presbyopic'))
        model(3,2)=model(3,2)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,1),'young'))
        model(1,3)=model(1,3)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,1),'pre-presbyopic'))
        model(2,3)=model(2,3)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,1),'presbyopic'))
        model(3,3)=model(3,3)+1;
    end
end
for i=2:1:N
    %??spectacle-prescrip    
    if(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,2),'myope'))
       model(4,1)=model(4,1)+1;
    elseif(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,2),'hypermetrope'))
       model(5,1)=model(5,1)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,2),'myope'))
        model(4,2)=model(4,2)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,2),'hypermetrope'))
        model(5,2)=model(5,2)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,2),'myope'))
        model(4,3)=model(4,3)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,2),'hypermetrope'))
        model(5,3)=model(5,3)+1;
     end
end
for i=2:1:N
    %??astigmatism    
    if(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,3),'yes'))
       model(6,1)=model(6,1)+1;
    elseif(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,3),'no'))
       model(7,1)=model(7,1)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,3),'yes'))
       model(6,2)=model(6,2)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,3),'no'))
       model(7,2)=model(7,2)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,3),'yes'))
       model(6,3)=model(6,3)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,3),'no'))
       model(7,3)=model(7,3)+1;
    end
end
for i=2:1:N
    %??tear-prod-rate   
    if(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,4),'reduced'))
       model(8,1)=model(8,1)+1;
    elseif(strcmp(traindata(i,5),'none')&&strcmp(traindata(i,4),'normal'))
       model(9,1)=model(9,1)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,4),'reduced'))
       model(8,2)=model(8,2)+1;
    elseif(strcmp(traindata(i,5),'hard')&&strcmp(traindata(i,4),'normal'))
       model(9,2)=model(9,2)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,4),'reduced'))
       model(8,3)=model(8,3)+1;
    elseif(strcmp(traindata(i,5),'soft')&&strcmp(traindata(i,4),'normal'))
       model(9,3)=model(9,3)+1;
    end
end
model(:,1)=model(:,1)/num_none;
model(:,2)=model(:,2)/num_hard;
model(:,3)=model(:,3)/num_soft;
disp(model);

%?model??bayes.model?
fid=fopen('bayes.model','w');
fprintf(fid,'%8.4f %8.4f %8.4f\n',model');  


        
        
    
    