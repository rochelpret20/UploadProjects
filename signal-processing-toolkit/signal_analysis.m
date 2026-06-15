clc;
clear;
close all;

Fs = 1000;
t = 0:1/Fs:2;

signal = sin(2*pi*10*t) ...
       + 0.5*sin(2*pi*50*t) ...
       + 0.8*randn(size(t));

figure;
plot(t,signal);
title('Original Signal');
xlabel('Time (s)');
ylabel('Amplitude');

N = length(signal);

f = (0:N-1)*(Fs/N);

Y = abs(fft(signal));

figure;
plot(f,Y);
title('FFT Spectrum');
xlabel('Frequency (Hz)');
ylabel('Magnitude');

fc = 20;

[b,a] = butter(4,fc/(Fs/2));

filtered = filtfilt(b,a,signal);

figure;
plot(t,signal);
hold on;
plot(t,filtered,'LineWidth',2);

legend('Original','Filtered');

title('Low Pass Filtering');

snr_before = snr(signal);

snr_after = snr(filtered);

fprintf('SNR Before: %.2f dB\n',snr_before);
fprintf('SNR After : %.2f dB\n',snr_after);